package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;

public class SculkIslandRitual extends StructureRitual {
    public SculkIslandRitual() {
        super(ArsCaelum.prefix("sculk"),  new BlockPos(-13, -5, -13), 10000, Biomes.DEEP_DARK);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Deep Dark";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Sculk and turns the biome to Deep Dark. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.SCULK);
    }
}
