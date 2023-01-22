package com.example.an_addon.ritual;

import com.example.an_addon.ExampleANAddon;
import com.example.an_addon.util.ManhattenTracker;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class PlatformRitual extends AbstractRitual {

    ManhattenTracker tracker;
    int radius = 0;
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
        BlockPos pos = getPos();
        BlockPos nextPos = tracker.computeNext();
        if(nextPos == null) {
            setFinished();
            return;
        }
        double x = nextPos.getX() + 0.5;
        double y = nextPos.getY() + 0.5;
        double z = nextPos.getZ() + 0.5;
        double dist = BlockUtil.distanceFrom(new Vec3(x, y, z), new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
        if (dist <= radius) {
            if(nextPos.getY() == pos.getY() - 1){
                getWorld().setBlock(nextPos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
            }else {
                getWorld().setBlock(nextPos, Blocks.DIRT.defaultBlockState(), 2);
            }
            getWorld().playSound(null, nextPos, Blocks.DIRT.defaultBlockState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }


    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ExampleANAddon.MODID, "platform_ritual");
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
        return "Creates an island of grass and dirt in a circle around the ritual. The island will generate with a radius of 7 blocks. Augmenting the ritual with Source Gems will increase the radius by 1 for each gem.";
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
