package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class FlourishingIslandRitual extends StructureRitual{
    public FlourishingIslandRitual() {
        super(new ResourceLocation(ArsCaelum.MODID, "flourishing"),  new BlockPos(-13, -5, -13), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, "flourishing_island");
    }
}
