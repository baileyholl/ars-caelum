package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ExampleANAddon;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.ars_caelum.ritual.features.BonemealFeature;
import com.hollingsworth.ars_caelum.ritual.features.RandomTreeFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class ForestationRitual extends FeaturePlacementRitual {


    @Override
    void addFeatures(List<IPlaceableFeature> features) {
        features.add(new RandomTreeFeature(List.of(Blocks.OAK_SAPLING.defaultBlockState(), Blocks.BIRCH_SAPLING.defaultBlockState(), Blocks.SPRUCE_SAPLING.defaultBlockState()), 5));
        features.add(new BonemealFeature(8));
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ExampleANAddon.MODID, RitualLang.FORESTATION);
    }
}
