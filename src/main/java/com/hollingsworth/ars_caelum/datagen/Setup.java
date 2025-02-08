package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ArsCaelum.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Setup {

    //use runData configuration to generate stuff, event.includeServer() for data, event.includeClient() for assets
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        gen.addProvider(event.includeServer(), new ArsProviders.EnchantingAppProvider(gen));
        gen.addProvider(event.includeServer(), new ArsProviders.CrushProvider(gen));
        gen.addProvider(event.includeClient(), new LangGen(gen.getPackOutput(), ArsCaelum.MODID, "en_us"));
        gen.addProvider(event.includeServer(), new RecipeData(gen.getPackOutput(), event.getLookupProvider()));
        gen.addProvider(event.includeClient(), new ItemModelGen(gen.getPackOutput(), ArsCaelum.MODID, event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new ItemTagDatagen(gen.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
    }
}
