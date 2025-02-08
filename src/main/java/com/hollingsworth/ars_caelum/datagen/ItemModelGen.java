package com.hollingsworth.ars_caelum.datagen;

import com.google.common.base.Preconditions;
import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.hollingsworth.arsnouveau.setup.registry.RegistryHelper.getRegistryName;

public class ItemModelGen extends ItemModelProvider {
    public ItemModelGen(PackOutput pack, String modid, ExistingFileHelper existingFileHelper) {
        super(pack, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RitualTablet i : RitualRegistry.getRitualItemMap().values()) {
            if(!i.ritual.getRegistryName().getNamespace().equals(ArsCaelum.MODID))
                continue;
            try {
                getBuilder(i.ritual.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", itemTexture(i));
            } catch (Exception e) {
                System.out.println("No texture for " + i);
            }
        }
    }

    private ResourceLocation itemTexture(final Item item) {
        final ResourceLocation name = registryName(item);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "item" + "/" + name.getPath());
    }

    private ResourceLocation registryName(final Item item) {
        return Preconditions.checkNotNull(getRegistryName(item), "Item %s has a null registry name", item);
    }

    private ResourceLocation registryName(final Block item) {
        return Preconditions.checkNotNull(getRegistryName(item), "Item %s has a null registry name", item);
    }


    private ResourceLocation itemTexture(final Block item) {
        final ResourceLocation name = registryName(item);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "items" + "/" + name.getPath());
    }

    private ResourceLocation spellTexture(final Item item) {
        final ResourceLocation name = registryName(item);
        return ResourceLocation.fromNamespaceAndPath(   name.getNamespace(), "items" + "/" + name.getPath().replace("glyph_", ""));
    }
}