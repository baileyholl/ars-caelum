package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class GeodeIslandRitual extends StructureRitual{
    public GeodeIslandRitual() {
        super(new ResourceLocation(ArsCaelum.MODID, "geode"),  new BlockPos(-8, -3, -7), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.GEODE);
    }
}
