package com.hollingsworth.ars_caelum;

import com.hollingsworth.ars_caelum.config.CaelumConfig;
import com.hollingsworth.ars_caelum.network.ACNetwork;
import com.hollingsworth.ars_caelum.registry.ModRegistry;
import com.hollingsworth.arsnouveau.setup.config.ANModConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(ArsCaelum.MODID)
public class ArsCaelum
{
    public static final String MODID = "ars_caelum";

    private static final Logger LOGGER = LogManager.getLogger();

    public ArsCaelum() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistry.registerRegistries(modbus);
        ArsNouveauRegistry.registerGlyphs();
        modbus.addListener(this::setup);
        modbus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        ANModConfig serverConfig = new ANModConfig(ModConfig.Type.SERVER, CaelumConfig.SERVER_CONFIG, ModLoadingContext.get().getActiveContainer(),ArsCaelum.MODID + "-server");
        ModLoadingContext.get().getActiveContainer().addConfig(serverConfig);
    }

    public static ResourceLocation prefix(String path){
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ACNetwork.registerMessages();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

}
