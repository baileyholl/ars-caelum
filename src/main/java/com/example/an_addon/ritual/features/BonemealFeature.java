package com.example.an_addon.ritual.features;

import com.example.an_addon.ritual.FeaturePlacementRitual;
import com.example.an_addon.ritual.IPlaceableFeature;
import com.hollingsworth.arsnouveau.api.ANFakePlayer;
import com.hollingsworth.arsnouveau.common.block.tile.RitualBrazierTile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BonemealFeature implements IPlaceableFeature {

    double distance;
    public BonemealFeature(double distance){
        this.distance = distance;
    }

    @Override
    public double distanceFromOthers() {
        return distance;
    }

    @Override
    public boolean onPlace(Level level, BlockPos pos, FeaturePlacementRitual placementRitual, RitualBrazierTile brazierTile) {
        ItemStack stack = new ItemStack(Items.BONE_MEAL, 64);
        if (BoneMealItem.applyBonemeal(stack, level, pos.below(), ANFakePlayer.getPlayer((ServerLevel) level))) {
            if (!level.isClientSide) {
                level.levelEvent(1505, pos.below(), 0); //particles
            }
            return true;
        }
        return false;
    }

    @Override
    public String getFeatureName() {
        return null;
    }
}
