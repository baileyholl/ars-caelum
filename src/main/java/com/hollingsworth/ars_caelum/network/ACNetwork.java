package com.hollingsworth.ars_caelum.network;

import com.hollingsworth.ars_caelum.ArsCaelum;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ACNetwork {
    public static SimpleChannel INSTANCE;

    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ArsCaelum.MODID, "network"), () -> "1.0", s -> true, s -> true);
    }
}
