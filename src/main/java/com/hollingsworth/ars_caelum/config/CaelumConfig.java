package com.hollingsworth.ars_caelum.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class CaelumConfig {
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec.ConfigValue<String> STARTER_ISLAND_RL;
    public static ForgeConfigSpec.ConfigValue<List<Integer>> STARTER_RITUAL_OFFSET;
    public static ForgeConfigSpec.IntValue STARTER_RITUAL_SOURCE;
    public static ForgeConfigSpec.ConfigValue<String> STARTER_RITUAL_BIOME;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        SERVER_BUILDER.push("starter_ritual");
        STARTER_ISLAND_RL = SERVER_BUILDER.comment("The ResourceLocation of the structure to use for the starter island. Default: ars_caelum:starter_island").define("starter_island_rl", "ars_caelum:starter_island");
        STARTER_RITUAL_OFFSET = SERVER_BUILDER.comment("The offset of the structure position for the starting island ritual").define("ritual_offset", List.of(-7, -5, -9), (t) -> true);
        STARTER_RITUAL_SOURCE = SERVER_BUILDER.comment("The amount of source required to perform the starting island ritual").defineInRange("ritual_source", 0, 0, 10000);
        STARTER_RITUAL_BIOME = SERVER_BUILDER.comment("The biome to use for the starting island ritual, like minecraft:plains. Providing nothing will not change the biome.").define("ritual_biome", "");
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}
