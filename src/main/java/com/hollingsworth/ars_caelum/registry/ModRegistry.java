package com.hollingsworth.ars_caelum.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.hollingsworth.ars_caelum.ArsCaelum.MODID;

public class ModRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);


    public static void registerRegistries(IEventBus bus){
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

//    public static final RegistryObject<Item> EXAMPLE;

    static {
//        EXAMPLE = ITEMS.register("star_hat", () -> new ExampleCosmetic(new Item.Properties().tab(ArsNouveau.itemGroup)));
    }

}
