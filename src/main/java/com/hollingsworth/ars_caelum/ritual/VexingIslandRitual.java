package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;

public class VexingIslandRitual extends StructureRitual {
    public VexingIslandRitual() {
        super(ArsCaelum.prefix("vexing"),  new BlockPos(-16, -5, -13), 10000, Biomes.MUSHROOM_FIELDS);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.VEXING);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Vexing";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Vexing Archwoods, source berries, mycelium, and mushrooms. Converts the area to Mushroom Island. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }
}
