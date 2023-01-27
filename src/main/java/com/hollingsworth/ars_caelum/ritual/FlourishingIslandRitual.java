package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class FlourishingIslandRitual extends StructureRitual{
    public FlourishingIslandRitual() {
        super(new ResourceLocation(ArsCaelum.MODID, "flourishing"),  new BlockPos(-13, -5, -13), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.FLOURISHING);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Flourishing";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Flourishing Archwoods, sugarcane, moss, and bamboo. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }
}
