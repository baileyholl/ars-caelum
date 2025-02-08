package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.setup.registry.BiomeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class BlazingIslandRitual extends StructureRitual {
    public BlazingIslandRitual() {
        super(ArsCaelum.prefix("blazing"), new BlockPos(-13, -5, -13), 10000, BiomeRegistry.ARCHWOOD_FOREST);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.BLAZING);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Blazing";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Blazing Archwoods and lava, converts the area to Archwood Forest. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(255, 100, 100);
    }
}
