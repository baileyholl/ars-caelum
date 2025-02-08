package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;

public class FlourishingIslandRitual extends StructureRitual {
    public FlourishingIslandRitual() {
        super(ArsCaelum.prefix("flourishing"),  new BlockPos(-13, -5, -13), 10000, Biomes.MANGROVE_SWAMP);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.FLOURISHING);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Flourishing";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of Flourishing Archwoods, moss, glow berries, and bamboo. Converts the area to Mangrove Swamp. Requires a full jar of Source to begin. NOTE: This ritual should be performed at least 14 blocks from any other block. ";
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(100, 255, 100);
    }
}
