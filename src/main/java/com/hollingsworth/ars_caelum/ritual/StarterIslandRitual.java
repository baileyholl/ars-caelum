package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.config.CaelumConfig;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class StarterIslandRitual extends StructureRitual {
    public StarterIslandRitual() {
        super(null, BlockPos.ZERO, 0, null);
    }


    @Override
    public void setup() {
        if(!getWorld().isClientSide) {
            this.structure = new ResourceLocation(CaelumConfig.STARTER_ISLAND_RL.get());
            this.offset = new BlockPos(CaelumConfig.STARTER_RITUAL_OFFSET.get().get(0), CaelumConfig.STARTER_RITUAL_OFFSET.get().get(1), CaelumConfig.STARTER_RITUAL_OFFSET.get().get(2));
            this.sourceRequired = CaelumConfig.STARTER_RITUAL_SOURCE.get();
            if (!CaelumConfig.STARTER_RITUAL_BIOME.get().isEmpty()) {
                Biome biome1 = BuiltinRegistries.BIOME.get(new ResourceLocation(CaelumConfig.STARTER_RITUAL_BIOME.get()));
                if (biome1 != null) {
                    this.biome = BuiltinRegistries.BIOME.getResourceKey(biome1).orElse(null);
                }
            }
        }
        super.setup();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.STARTER);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Starter";
    }

    @Override
    public boolean canBeTraded() {
        return false;
    }
}
