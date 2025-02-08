package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CobbleRitual extends AbstractRitual {
    int converted;
    @Override
    protected void tick() {
        int range = 1;
        if(getWorld().isClientSide){
            ParticleUtil.spawnRitualAreaEffect(getPos(), getWorld(), getWorld().getRandom(), getCenterColor(), range);
        }else{
            if(getWorld().getGameTime() % 15 == 0){
                for(BlockPos pos : BlockPos.betweenClosed(getPos().offset(-range, -1, -range), getPos().offset(range, 1, range))){
                    if(getWorld().getBlockState(pos).getFluidState().isSource() && getWorld().getBlockState(pos).is(Blocks.WATER) ){
                        getWorld().setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        getWorld().playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                        converted++;
                        if(converted >= 4){
                            converted = 0;
                            boolean isDeepslate = getConsumedItems().stream().anyMatch(itemStack -> itemStack.getItem() == Blocks.DEEPSLATE.defaultBlockState().getBlock().asItem());
                            BlockState state = isDeepslate ? Blocks.DEEPSLATE.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState();
                            getWorld().setBlockAndUpdate(pos, state);
                        }
                        return;
                    }
                }
            }
        }
    }

    @Override
    public boolean canConsumeItem(ItemStack stack) {
        boolean isDeepslate = getConsumedItems().stream().anyMatch(itemStack -> itemStack.getItem() == Blocks.DEEPSLATE.defaultBlockState().getBlock().asItem());
        return super.canConsumeItem(stack) || (!isDeepslate && stack.getItem() == Blocks.DEEPSLATE.defaultBlockState().getBlock().asItem());
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(100, 200, 255);
    }

    @Override
    public ParticleColor getOuterColor() {
        return new ParticleColor(100, 200, 255);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.COBBLE);
    }


    @Override
    public String getLangName() {
        return "Sedimentation";
    }

    @Override
    public String getLangDescription() {
        return "Converts four nearby water sources and will produce cobblestone on the last source consumed. Augment with Deepslate to produce Deepslate instead.";
    }

}
