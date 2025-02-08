package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class GeodeIslandRitual extends StructureRitual {
    public GeodeIslandRitual() {
        super(ArsCaelum.prefix("geode"),  new BlockPos(-8, -3, -7), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.GEODE);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Geode";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island in the shape of an Amethyst Geode. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }
}
