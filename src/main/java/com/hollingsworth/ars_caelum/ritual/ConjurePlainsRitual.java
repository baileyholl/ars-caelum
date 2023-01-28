package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ConjurePlainsRitual extends ConjureBiomeRitual {

    public ConjurePlainsRitual() {
        super(Biomes.PLAINS);
    }

    @Override
    public BlockState stateForPos(BlockPos nextPos) {
        return nextPos.getY() == getPos().getY() - 1 ? Blocks.GRASS_BLOCK.defaultBlockState() : Blocks.DIRT.defaultBlockState();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.PLAINS);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Plains";
    }

    @Override
    public String getLangDescription() {
        return "Creates an island of grass and dirt in a circle around the ritual, converting the area to Plains. The island will generate with a radius of 7 blocks. Augmenting the ritual with Source Gems will increase the radius by 1 for each gem. Source must be provided nearby as blocks are generated.";
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(100, 255, 100);
    }
}
