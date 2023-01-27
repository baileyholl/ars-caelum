package com.hollingsworth.ars_caelum.ritual.features;

import com.hollingsworth.ars_caelum.ritual.FeaturePlacementRitual;
import com.hollingsworth.ars_caelum.ritual.IPlaceableFeature;
import com.hollingsworth.arsnouveau.common.block.tile.RitualBrazierTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class PlaceBlockFeature implements IPlaceableFeature {

    public double distance;
    public Supplier<BlockState> block;
    public double chance;
    public PlaceBlockFeature(double distance, double chance, Supplier<BlockState> block){
        this.distance = distance;
        this.block = block;
        this.chance = chance;
    }

    @Override
    public double distanceFromOthers() {
        return distance;
    }

    @Override
    public boolean onPlace(Level level, BlockPos pos, FeaturePlacementRitual placementRitual, RitualBrazierTile brazierTile) {
        BlockState state = block.get();
        if(level.random.nextFloat() < chance && !level.getBlockState(pos.below()).isAir() && state.canSurvive(level, pos)){
            BlockItem stack = (BlockItem) state.getBlock().asItem();
            stack.place(new BlockPlaceContext(level, null, InteractionHand.MAIN_HAND, new ItemStack(stack), new BlockHitResult(new Vec3(pos.getX(), pos.getY(), pos.getZ()), Direction.DOWN, pos, false)));
            return true;
        }
        return false;
    }

    @Override
    public String getFeatureName() {
        return "block";
    }
}
