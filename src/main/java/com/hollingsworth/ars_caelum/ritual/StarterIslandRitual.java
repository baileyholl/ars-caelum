package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.config.CaelumConfig;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;

public class StarterIslandRitual extends StructureRitual {
    public StarterIslandRitual() {
        super(null, BlockPos.ZERO, 0, null);
    }


    @Override
    public void setup() {
        if(!getWorld().isClientSide) {
            this.structure = ResourceLocation.parse(CaelumConfig.STARTER_ISLAND_RL.get());
            this.offset = new BlockPos(CaelumConfig.STARTER_RITUAL_OFFSET.get().get(0), CaelumConfig.STARTER_RITUAL_OFFSET.get().get(1), CaelumConfig.STARTER_RITUAL_OFFSET.get().get(2));
            this.sourceRequired = CaelumConfig.STARTER_RITUAL_SOURCE.get();
            if (!CaelumConfig.STARTER_RITUAL_BIOME.get().isEmpty()) {
                getWorld().registryAccess().registry(Registries.BIOME).ifPresent(biomes -> {
                    Biome biome1 = biomes.get(ResourceLocation.parse(CaelumConfig.STARTER_RITUAL_BIOME.get()));
                    if (biome1 != null) {
                        Optional<ResourceKey<Biome>> biomeKey = biomes.getResourceKey(biome1);
                        biomeKey.ifPresent(biomeResourceKey -> {
                            this.biome = biomeResourceKey;
                        });
                    }
                });
            }
        }
        super.setup();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.STARTER);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Starter";
    }
}
