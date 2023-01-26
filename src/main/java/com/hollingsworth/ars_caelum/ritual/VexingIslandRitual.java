package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class VexingIslandRitual extends StructureRitual{
    public VexingIslandRitual() {
        super(new ResourceLocation(ArsCaelum.MODID, "vexing"),  new BlockPos(-16, -5, -13), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.VEXING);
    }
}
