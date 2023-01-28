package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import com.hollingsworth.arsnouveau.setup.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeData extends com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen {
    public RecipeData(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shapelessBuilder(getAddonRitual(RitualLang.COBBLE)).requires(BlockRegistry.CASCADING_LOG).requires(BlockRegistry.FROSTAYA_POD, 8).save(consumer);

        shapelessBuilder(getAddonRitual(RitualLang.BLAZING)).requires(BlockRegistry.BLAZING_SAPLING).requires(BlockRegistry.BLAZING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.VEXING)).requires(BlockRegistry.VEXING_SAPLING).requires(BlockRegistry.VEXING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.FLOURISHING)).requires(BlockRegistry.FLOURISHING_SAPLING).requires(BlockRegistry.FLOURISHING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.CASCADING)).requires(BlockRegistry.CASCADING_SAPLING).requires(BlockRegistry.CASCADING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.PLAINS)).requires(BlockRegistry.FLOURISHING_LOG).requires(Blocks.GRASS_BLOCK).requires(ItemsRegistry.EARTH_ESSENCE).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.FORESTATION)).requires(BlockRegistry.FLOURISHING_LOG).requires(BlockRegistry.MENDOSTEEN_POD).requires(ItemsRegistry.EARTH_ESSENCE).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.FLOWERING)).requires(BlockRegistry.FLOURISHING_LOG).requires(Items.POPPY, 3).requires(Items.DANDELION, 3).requires(ItemsRegistry.EARTH_ESSENCE).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.MANA_REGEN)).requires(BlockRegistry.VEXING_LOG).requires(ItemsRegistry.ABJURATION_ESSENCE)
                .requires(Ingredient.of(Tags.Items.GEMS_DIAMOND), 3).requires(ItemTagProvider.SOURCE_GEM_BLOCK_TAG).requires(Items.BLAZE_ROD).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.END_PORTAL)).requires(BlockRegistry.VEXING_LOG).requires(Items.ENDER_EYE, 8).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.GEODE)).requires(BlockRegistry.VEXING_LOG).requires(Ingredient.of(ItemTagProvider.SOURCE_GEM_BLOCK_TAG), 3)
                .requires(Tags.Items.GEMS_DIAMOND).save(consumer);
        shapelessBuilder(getAddonRitual(RitualLang.DESERT)).requires(BlockRegistry.BLAZING_LOG).requires(Blocks.SAND).requires(ItemsRegistry.EARTH_ESSENCE).save(consumer);
        shapelessBuilder(Items.TURTLE_EGG).requires(Tags.Items.EGGS).requires(ItemsRegistry.CONJURATION_ESSENCE).save(consumer);
        shapelessBuilder(Items.POINTED_DRIPSTONE, 4).requires(ItemsRegistry.WATER_ESSENCE).requires(Ingredient.of(Tags.Items.STONE), 4).save(consumer);
    }

    public RitualTablet getAddonRitual(String name) {
        return ArsNouveauAPI.getInstance().getRitualItemMap().get(new ResourceLocation(ArsCaelum.MODID, name));
    }
}
