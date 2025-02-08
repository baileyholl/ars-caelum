package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class RecipeData extends com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen {
    public RecipeData(PackOutput pack, CompletableFuture<HolderLookup.Provider> lookup) {
        super(pack, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        shapelessBuilder(getAddonRitual(RitualLang.COBBLE)).requires(BlockRegistry.CASCADING_LOG).requires(BlockRegistry.FROSTAYA_POD, 8).save(output);

        shapelessBuilder(getAddonRitual(RitualLang.BLAZING)).requires(BlockRegistry.BLAZING_SAPLING).requires(BlockRegistry.BLAZING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(output);
        shapelessBuilder(getAddonRitual(RitualLang.VEXING)).requires(BlockRegistry.VEXING_SAPLING).requires(BlockRegistry.VEXING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(output);
        shapelessBuilder(getAddonRitual(RitualLang.FLOURISHING)).requires(BlockRegistry.FLOURISHING_SAPLING).requires(BlockRegistry.FLOURISHING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(output);
        shapelessBuilder(getAddonRitual(RitualLang.CASCADING)).requires(BlockRegistry.CASCADING_SAPLING).requires(BlockRegistry.CASCADING_LOG).requires(Tags.Items.GEMS_DIAMOND).save(output);
         shapelessBuilder(getAddonRitual(RitualLang.MANA_REGEN)).requires(BlockRegistry.VEXING_LOG).requires(ItemsRegistry.ABJURATION_ESSENCE)
                .requires(Ingredient.of(Tags.Items.GEMS_DIAMOND), 3).requires(ItemTagProvider.SOURCE_GEM_BLOCK_TAG).requires(Items.BLAZE_ROD).save(output);
        shapelessBuilder(getAddonRitual(RitualLang.END_PORTAL)).requires(BlockRegistry.VEXING_LOG).requires(Items.ENDER_EYE, 8).save(output);
        shapelessBuilder(getAddonRitual(RitualLang.GEODE)).requires(BlockRegistry.VEXING_LOG).requires(Ingredient.of(ItemTagProvider.SOURCE_GEM_BLOCK_TAG), 3)
                .requires(Tags.Items.GEMS_DIAMOND).save(output);

        shapelessBuilder(Items.TURTLE_EGG).requires(Tags.Items.EGGS).requires(ItemsRegistry.CONJURATION_ESSENCE).save(output);
        shapelessBuilder(Items.POINTED_DRIPSTONE, 4).requires(ItemsRegistry.WATER_ESSENCE).requires(Ingredient.of(Tags.Items.STONES), 4).save(output);
        shapelessBuilder(getAddonRitual(RitualLang.SCULK)).requires(BlockRegistry.VEXING_LOG).requires(Items.DEEPSLATE, 4)
                .requires(Ingredient.of(Blocks.AMETHYST_BLOCK), 4).save(output);

        shapelessBuilder(getAddonRitual(RitualLang.VILLAGE)).requires(BlockRegistry.FLOURISHING_SAPLING).requires(BlockRegistry.BLAZING_SAPLING).requires(BlockRegistry.CASCADING_SAPLING).requires(Blocks.AMETHYST_BLOCK).requires(Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .requires(BlockRegistry.CASCADING_LOG).requires(Tags.Items.RODS_BLAZE).save(output);

        shapelessBuilder(getAddonRitual(RitualLang.ELDER_SUMMON))
                .requires(Ingredient.of(ItemTags.FISHES), 3)
                .requires(BlockRegistry.CASCADING_LOG).requires(Tags.Items.RODS_BLAZE).save(output);
    }

    public RitualTablet getAddonRitual(String name) {
        return RitualRegistry.getRitualItemMap().get(ArsCaelum.prefix(name));
    }
}
