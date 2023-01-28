package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.ars_caelum.util.ManhattenTracker;
import com.hollingsworth.ars_caelum.util.RitualUtil;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class ConjurePlainsRitual extends AbstractRitual {

    ManhattenTracker tracker;
    int radius = 0;
    int blocksPlaced;

    @Override
    public void onStart() {
        super.onStart();
        if(getWorld().isClientSide){
            return;
        }
        radius = 7;
        for(ItemStack i : getConsumedItems()){
            radius += i.getCount();
        }
        tracker = new ManhattenTracker(getPos().below(3), radius, 2, radius);
    }

    @Override
    protected void tick() {
        if(getWorld().isClientSide){
            return;
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
                RitualUtil.changeBiome(getWorld(), nextPos, Biomes.PLAINS);
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

    @Override
    public int getSourceCost() {
        return 50;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.PLAINS);
    }

    @Override
    public boolean canConsumeItem(ItemStack stack) {
        return stack.is(ItemTagProvider.SOURCE_GEM_TAG);
    }

    @Override
    public String getLangName() {
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
        radius = tag.getInt("radius");
    }

    @Override
    public void write(CompoundTag tag) {
        super.write(tag);
        if(tracker != null){
            tag.put("tracker", tracker.serialize(new CompoundTag()));
        }
        tag.putInt("radius", radius);
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(100, 255, 100);
    }
}
