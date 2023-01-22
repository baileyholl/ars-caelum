package com.example.an_addon.ritual;

import com.example.an_addon.ExampleANAddon;
import com.example.an_addon.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForestationRitual extends AbstractRitual {

    Map<String, List<BlockPos>> featurePlacedMap = new HashMap<>();

    @Override
    protected void tick() {
        if(getWorld().isClientSide){
            return;
        }
        int radius = 7;
        for(BlockPos pos : BlockPos.betweenClosed(getPos().offset(-radius, 0, -radius), getPos().offset(radius, 0, radius))){
            if(BlockUtil.distanceFrom(pos, getPos()) > radius){
                continue;
            }
        }

    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ExampleANAddon.MODID, RitualLang.FORESTATION);
    }
}
