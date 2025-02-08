package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;

public class CascadingIslandRitual extends StructureRitual {
    public CascadingIslandRitual() {
        super(ArsCaelum.prefix("cascading"),  new BlockPos(-13, -5, -13), 10000, Biomes.WARM_OCEAN);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.CASCADING);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Cascading";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Cascading Archwoods, coral, and sugarcane. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(100, 100, 255);
    }
}
