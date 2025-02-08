package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagDatagen extends IntrinsicHolderTagsProvider<Item> {
    public ItemTagDatagen(PackOutput arg, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(arg, Registries.ITEM, future, item -> item.builtInRegistryHolder().key(), ArsCaelum.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        blacklistRitual(RitualLang.BLAZING);
        blacklistRitual(RitualLang.CASCADING);
        blacklistRitual(RitualLang.END_PORTAL);
        blacklistRitual(RitualLang.FLOURISHING);
        blacklistRitual(RitualLang.GEODE);
        blacklistRitual(RitualLang.SCULK);
        blacklistRitual(RitualLang.STARTER);
        blacklistRitual(RitualLang.VEXING);
        blacklistRitual(RitualLang.VILLAGE);
    }

    private void blacklistRitual(String ritual) {
        ResourceLocation location = ArsCaelum.prefix(ritual);
        RitualTablet tablet = RitualRegistry.getRitualItemMap().get(location);
        this.tag(ItemTagProvider.RITUAL_TRADE_BLACKLIST).add(tablet);
        this.tag(ItemTagProvider.RITUAL_LOOT_BLACKLIST).add(tablet);
    }
}
