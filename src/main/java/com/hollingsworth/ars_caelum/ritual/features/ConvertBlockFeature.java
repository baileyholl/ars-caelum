package com.hollingsworth.ars_caelum.ritual.features;

import com.hollingsworth.ars_caelum.ritual.FeaturePlacementRitual;
import com.hollingsworth.ars_caelum.ritual.IPlaceableFeature;
import com.hollingsworth.arsnouveau.common.block.tile.RitualBrazierTile;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class ConvertBlockFeature implements IPlaceableFeature {

    public double distance;
    public double chance;
    Function<BlockState, Boolean> convertable;
    Function<BlockState, BlockState> convert;

    public ConvertBlockFeature(double distance, double chance, Function<BlockState, Boolean> convertable, Function<BlockState, BlockState> convert){
        this.distance = distance;
        this.chance = chance;
        this.convertable = convertable;
        this.convert = convert;
    }

    @Override
    public double distanceFromOthers() {
        return distance;
    }

    @Override
    public boolean onPlace(Level level, BlockPos pos, FeaturePlacementRitual placementRitual, RitualBrazierTile brazierTile) {
        BlockState state = level.getBlockState(pos);
        if(level.random.nextFloat() < chance && convertable.apply(state)){
            level.setBlockAndUpdate(pos, convert.apply(state));
            level.playSound(null, pos, state.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        }
        return false;
    }

    @Override
    public String getFeatureName() {
        return "convert_blockstate";
    }
}
