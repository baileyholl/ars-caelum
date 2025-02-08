package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.ritual.StarterIslandRitual;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.common.crafting.recipes.*;
import com.hollingsworth.arsnouveau.common.datagen.*;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.CraftingPage;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.PatchouliBuilder;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.Tags;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArsProviders {

    static String root = ArsCaelum.MODID;

    public static class CrushProvider extends CrushRecipeProvider {
        public DataGenerator generator;
        public List<CrushWrapper> replaceAn = new ArrayList<>();
        public CrushProvider(DataGenerator generatorIn) {
            super(generatorIn);
            this.generator = generatorIn;
        }

        @Override
        public void collectJsons(CachedOutput cache) {
            CrushRecipe.CrushOutput goldNug = new CrushRecipe.CrushOutput(Items.GOLD_NUGGET.getDefaultInstance(), 0.2f, 3);
            CrushRecipe.CrushOutput ironNug = new CrushRecipe.CrushOutput(Items.IRON_NUGGET.getDefaultInstance(), 0.2f, 3);
            CrushRecipe.CrushOutput copperNug = new CrushRecipe.CrushOutput(Items.RAW_COPPER.getDefaultInstance(), 0.2f, 1);

            replaceAn.add(
                new CrushWrapper("stone", Ingredient.of(Tags.Items.STONES))
                    .withItems(Items.GRAVEL.getDefaultInstance())
                    .withItems(goldNug)
                    .withItems(ironNug)
                    .withItems(copperNug)
            );

            replaceAn.add(
                new CrushWrapper("cobblestone", Ingredient.of(Tags.Items.COBBLESTONES))
                    .withItems(Items.GRAVEL.getDefaultInstance())
                    .withItems(goldNug)
                    .withItems(ironNug)
                    .withItems(copperNug)
            );

            replaceAn.add(
                new CrushWrapper("gravel", Ingredient.of(Tags.Items.GRAVELS))
                    .withItems(Items.SAND.getDefaultInstance())
                    .withItems(Items.LAPIS_LAZULI.getDefaultInstance(), 0.05f)
                    .withItems(Items.DIAMOND.getDefaultInstance(), 0.01f)
                    .withItems(Items.FLINT.getDefaultInstance(), 0.02f)
            );

            replaceAn.add(
                    new CrushWrapper("sand", Ingredient.of(Tags.Items.SANDS))
                    .withItems(Items.CLAY_BALL.getDefaultInstance(), 0.05f)
                    .withItems(Items.REDSTONE.getDefaultInstance(), 0.05f)
                    .skipBlockPlace()
            );

            for (CrushWrapper g : replaceAn) {
                Path path = getANPath(output, g.path.getPath());
                saveStable(cache, CrushRecipe.CODEC.encodeStart(JsonOps.INSTANCE, g.asRecipe()).getOrThrow(), path);
            }
        }

        private static Path getANPath(Path pathIn, String str) {
            return pathIn.resolve("data/" + ArsNouveau.MODID + "/recipe/" + str + ".json");
        }

        public static class CrushWrapper {
            public ResourceLocation path;
            public Ingredient ing;
            private boolean shouldSkipBlockPlace = false;

            public CrushWrapper(String string, Ingredient ingredient){
                this.path = ArsNouveau.prefix(string);
                this.ing = ingredient;
            }
            List<CrushRecipe.CrushOutput> outputs = new ArrayList<>();

            public CrushWrapper withItems(ItemStack output, float chance) {
                this.outputs.add(new CrushRecipe.CrushOutput(output, chance));
                return this;
            }

            public CrushWrapper withItems(ItemStack output) {
                this.outputs.add(new CrushRecipe.CrushOutput(output, 1.0f));
                return this;
            }

            public CrushWrapper withItems(CrushRecipe.CrushOutput output) {
                this.outputs.add(output);
                return this;
            }

            public CrushWrapper skipBlockPlace() {
                this.shouldSkipBlockPlace = true;
                return this;
            }

            public CrushRecipe asRecipe() {
                return new CrushRecipe(this.ing, outputs, shouldSkipBlockPlace);
            }
        }
    }

    public static class EnchantingAppProvider extends ApparatusRecipeProvider {

        public EnchantingAppProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {

            recipes.add(builder()
                    .withReagent(Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                    .withPedestalItem(8, Items.NAUTILUS_SHELL)
                    .withResult(Items.HEART_OF_THE_SEA)
                    .withSourceCost(10000)
                    .build()
            );

            recipes.add(builder()
                    .withPedestalItem(3,Items.SCULK_SENSOR)
                    .buildEnchantmentRecipe(Enchantments.SWIFT_SNEAK, 1, 3000));
            recipes.add(builder()
                    .withPedestalItem(3,Items.SCULK_SHRIEKER)
                    .buildEnchantmentRecipe(Enchantments.SWIFT_SNEAK, 2, 6000));

            recipes.add(builder()
                    .withPedestalItem(3,Items.SCULK_CATALYST)
                    .buildEnchantmentRecipe(Enchantments.SWIFT_SNEAK, 3, 9000));

            for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> wrapper : recipes) {
                Path path = getRecipePath(output, wrapper.id().getPath());
                saveStable(cache, wrapper.serialize(), path);
            }
        }

        protected static Path getRecipePath(Path pathIn, String str){
            return pathIn.resolve("data/"+ root +"/recipe/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Example Apparatus";
        }
    }
}
