package com.hollingsworth.ars_caelum;

import com.hollingsworth.ars_caelum.config.CaelumConfig;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ArsCaelum.MODID)
public class ArsCaelum
{
    public static final String MODID = "ars_caelum";

    private static final Logger LOGGER = LogManager.getLogger();

    public ArsCaelum(IEventBus modbus, ModContainer modContainer) {
        ArsNouveauRegistry.registerGlyphs();
        modContainer.registerConfig(ModConfig.Type.SERVER, CaelumConfig.SERVER_CONFIG);
    }

    public static ResourceLocation prefix(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
