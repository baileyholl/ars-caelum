package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class BlazingIslandRitual extends StructureRitual{
    public BlazingIslandRitual() {
        super(new ResourceLocation(ArsCaelum.MODID, "blazing"), new BlockPos(-13, -5, -13));
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, "blazing_island");
    }

    @Override
    public String getName() {
        return "Conjure Island: Blazing";
    }
}
