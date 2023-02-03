package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class BlazingIslandRitual extends StructureRitual {
    public BlazingIslandRitual() {
        super(new ResourceLocation(ArsCaelum.MODID, "blazing"), new BlockPos(-13, -5, -13), 10000, null);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.BLAZING);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Blazing";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Blazing Archwoods and lava. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(255, 100, 100);
    }

    @Override
    public boolean canBeTraded() {
        return false;
    }
}
