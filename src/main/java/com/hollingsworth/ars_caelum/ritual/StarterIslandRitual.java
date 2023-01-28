package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.config.ExampleConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class StarterIslandRitual extends StructureRitual{
    public StarterIslandRitual() {
        super(null, BlockPos.ZERO, 0, null);
    }


    @Override
    public void setup() {
        if(!getWorld().isClientSide) {
            this.structure = new ResourceLocation(ExampleConfig.STARTER_ISLAND_RL.get());
            this.offset = new BlockPos(ExampleConfig.STARTER_RITUAL_OFFSET.get().get(0), ExampleConfig.STARTER_RITUAL_OFFSET.get().get(1), ExampleConfig.STARTER_RITUAL_OFFSET.get().get(2));
            this.sourceRequired = ExampleConfig.STARTER_RITUAL_SOURCE.get();
            if (!ExampleConfig.STARTER_RITUAL_BIOME.get().isEmpty()) {
                Biome biome1 = BuiltinRegistries.BIOME.get(new ResourceLocation(ExampleConfig.STARTER_RITUAL_BIOME.get()));
                if (biome1 != null) {
                    this.biome = BuiltinRegistries.BIOME.getResourceKey(biome1).orElse(null);
                }
            }
        }
        super.setup();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, "starter_island_ritual");
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Starter";
    }

}
