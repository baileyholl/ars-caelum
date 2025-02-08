package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class EndRitual extends StructureRitual {
    public EndRitual() {
        super(ArsCaelum.prefix("frame"), new BlockPos(-5,-1,-4), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.END_PORTAL);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: End Portal";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island with an end portal. This ritual requires a full jar of Source. NOTE: This ritual must be performed 14 blocks away from other blocks.";
    }
}
