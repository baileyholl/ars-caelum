package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.ars_caelum.network.ACNetwork;
import com.hollingsworth.ars_caelum.network.ChangeBiomePacket;
import com.hollingsworth.ars_caelum.util.ManhattenTracker;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

public class ConjurePlainsRitual extends AbstractRitual {

    ManhattenTracker tracker;
    int radius = 0;
    int blocksPlaced;
    @Override
    protected void tick() {
        if(getWorld().isClientSide){
            return;
        }
        if(radius == 0){
            radius = 7;
            for(ItemStack i : getConsumedItems()){
                radius += i.getCount();
            }
        }
        if(tracker == null){
            tracker = new ManhattenTracker(getPos().below(3), radius, 2, radius);
        }
        for(int i = 0; i < radius; i++) {
            BlockPos pos = getPos();
            BlockPos nextPos = tracker.computeNext();
            if (nextPos == null) {
                setFinished();
                return;
            }
            double x = nextPos.getX() + 0.5;
            double y = nextPos.getY() + 0.5;
            double z = nextPos.getZ() + 0.5;
            double dist = BlockUtil.distanceFrom(new Vec3(x, y, z), new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
            if (dist <= radius && getWorld().getBlockState(nextPos).getMaterial().isReplaceable()) {
                if (nextPos.getY() == pos.getY() - 1) {
                    getWorld().setBlock(nextPos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                } else {
                    getWorld().setBlock(nextPos, Blocks.DIRT.defaultBlockState(), 2);
                }
                changeBiome(getWorld(), nextPos, Biomes.PLAINS);
                getWorld().playSound(null, nextPos, Blocks.DIRT.defaultBlockState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
                blocksPlaced++;
                if (blocksPlaced >= 5){
                    blocksPlaced = 0;
                    setNeedsSource(true);
                }
                return;
            }
        }
    }

    public void changeBiome(Level level, BlockPos pos, ResourceKey<Biome> target) {
        Holder<Biome> biome = level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getHolderOrThrow(target);
//        int range = TFConfig.COMMON_CONFIG.MAGIC_TREES.transformationRange.get();
        BlockPos dPos = pos;

        if (level.getBiome(dPos).is(target))
            return;

        int minY = QuartPos.fromBlock(level.getMinBuildHeight());
        int maxY = minY + QuartPos.fromBlock(level.getHeight()) - 1;

        int x = QuartPos.fromBlock(dPos.getX());
        int z = QuartPos.fromBlock(dPos.getZ());

        LevelChunk chunkAt = level.getChunk(dPos.getX() >> 4, dPos.getZ() >> 4);
        for (LevelChunkSection section : chunkAt.getSections()) {
            for (int sy = 0; sy < 16; sy += 4) {
                int y = Mth.clamp(QuartPos.fromBlock(section.bottomBlockY() + sy), minY, maxY);
                if (section.getBiomes().get(x & 3, y & 3, z & 3).is(target))
                    continue;
                if (section.getBiomes() instanceof PalettedContainer<Holder<Biome>> container)
                    container.set(x & 3, y & 3, z & 3, biome);
            }
        }

        if (level instanceof ServerLevel server) {
            if (!chunkAt.isUnsaved()) chunkAt.setUnsaved(true);
            sendChangedBiome(chunkAt, dPos, target);
        }
    }

    private void sendChangedBiome(LevelChunk chunk, BlockPos pos, ResourceKey<Biome> biome) {
        ChangeBiomePacket message = new ChangeBiomePacket(pos, biome);
        ACNetwork.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), message);
    }

    @Override
    public int getSourceCost() {
        return 50;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.PLATFORM);
    }

    @Override
    public boolean canConsumeItem(ItemStack stack) {
        return stack.is(ItemTagProvider.SOURCE_GEM_TAG);
    }

    @Override
    public String getName() {
        return "Conjure Island: Plains";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of grass and dirt in a circle around the ritual, converting the area to Plains. The island will generate with a radius of 7 blocks. Augmenting the ritual with Source Gems will increase the radius by 1 for each gem. Source must be provided nearby as blocks are generated.";
    }

    @Override
    public void read(CompoundTag tag) {
        super.read(tag);
        if(tag.contains("tracker")){
            tracker = new ManhattenTracker(tag.getCompound("tracker"));
        }
    }

    @Override
    public void write(CompoundTag tag) {
        super.write(tag);
        if(tracker != null){
            tag.put("tracker", tracker.serialize(new CompoundTag()));
        }
    }
}
