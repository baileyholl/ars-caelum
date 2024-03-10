package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.ritual.StarterIslandRitual;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.common.crafting.recipes.CrushRecipe;
import com.hollingsworth.arsnouveau.common.crafting.recipes.GlyphRecipe;
import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.CrushRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.GlyphRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.CraftingPage;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.PatchouliBuilder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.Tags;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.hollingsworth.arsnouveau.setup.registry.RegistryHelper.getRegistryName;

public class ArsProviders {

    static String root = ArsCaelum.MODID;

    public static class CrushProvider extends CrushRecipeProvider {
        public DataGenerator generator;
        public List<CrushRecipe> replaceAn = new ArrayList<>();

        public CrushProvider(DataGenerator generatorIn) {
            super(generatorIn);
            this.generator = generatorIn;
        }

        @Override
        public void collectJsons(CachedOutput cache) {
            CrushRecipe.CrushOutput goldNug = new CrushRecipe.CrushOutput(Items.GOLD_NUGGET.getDefaultInstance(), 0.2f, 3);
            CrushRecipe.CrushOutput ironNug = new CrushRecipe.CrushOutput(Items.IRON_NUGGET.getDefaultInstance(), 0.2f, 3);
            // copper nug
            CrushRecipe.CrushOutput copperNug = new CrushRecipe.CrushOutput(Items.RAW_COPPER.getDefaultInstance(), 0.2f, 1);
            // stone recipe
            CrushRecipe stone = new CrushRecipe("stone", Ingredient.of(Tags.Items.STONE)).withItems(Items.GRAVEL.getDefaultInstance());
            stone.outputs.add(goldNug);
            stone.outputs.add(ironNug);
            stone.outputs.add(copperNug);

            CrushRecipe cobbleRecipe = new CrushRecipe("cobblestone", Ingredient.of(Tags.Items.COBBLESTONE))
                    .withItems(Items.GRAVEL.getDefaultInstance());
            cobbleRecipe.outputs.add(goldNug);
            cobbleRecipe.outputs.add(ironNug);
            cobbleRecipe.outputs.add(copperNug);
            replaceAn.add(cobbleRecipe);
            replaceAn.add(stone);
            replaceAn.add(new CrushRecipe("gravel", Ingredient.of(Tags.Items.GRAVEL))
                    .withItems(Items.SAND.getDefaultInstance())
                    .withItems(Items.LAPIS_LAZULI.getDefaultInstance(), 0.05f)
                    .withItems(Items.DIAMOND.getDefaultInstance(), 0.01f)
                    .withItems(Items.FLINT.getDefaultInstance(), 0.02f));

            replaceAn.add(new CrushRecipe("sand", Ingredient.of(Tags.Items.SAND))
                    .withItems(Items.CLAY_BALL.getDefaultInstance(), 0.05f)
                    .withItems(Items.REDSTONE.getDefaultInstance(), 0.05f).skipBlockPlace());
            for (CrushRecipe g : recipes) {
                Path path = getRecipePath(output, g.getId().getPath());
                saveStable(cache, g.asRecipe(), path);
            }

            for (CrushRecipe g : replaceAn) {
                Path path = getANPath(output, g.getId().getPath());
                saveStable(cache, g.asRecipe(), path);
            }
        }

        private static Path getRecipePath(Path pathIn, String str) {
            return pathIn.resolve("data/" + ArsCaelum.MODID + "/recipes/" + str + ".json");
        }

        private static Path getANPath(Path pathIn, String str) {
            return pathIn.resolve("data/" + ArsNouveau.MODID + "/recipes/" + str + ".json");
        }
    }

    public static class GlyphProvider extends GlyphRecipeProvider {

        public GlyphProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {

//            recipes.add(get(TestEffect.INSTANCE).withItem(Items.DIRT));

            for (GlyphRecipe recipe : recipes) {
                Path path = getScribeGlyphPath(output, recipe.output.getItem());
                saveStable(cache, recipe.asRecipe(), path);
            }

        }

        protected static Path getScribeGlyphPath(Path pathIn, Item glyph) {
            return pathIn.resolve("data/" + root + "/recipes/" + getRegistryName(glyph).getPath() + ".json");
        }

        @Override
        public String getName() {
            return "Caelum Glyph Recipes";
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
                    .withPedestalItem(3, Items.SCULK_SENSOR)
                    .buildEnchantmentRecipe(Enchantments.SWIFT_SNEAK, 1, 3000));
            recipes.add(builder()
                    .withPedestalItem(3, Items.SCULK_SHRIEKER)
                    .buildEnchantmentRecipe(Enchantments.SWIFT_SNEAK, 2, 6000));
            recipes.add(builder()
                    .withPedestalItem(3, Items.SCULK_CATALYST)
                    .buildEnchantmentRecipe(Enchantments.SWIFT_SNEAK, 3, 9000));
            for (EnchantingApparatusRecipe g : recipes) {
                if (g != null) {
                    Path path = getRecipePath(output, g.getId().getPath());
                    saveStable(cache, g.asRecipe(), path);
                }
            }

        }

        protected static Path getRecipePath(Path pathIn, String str) {
            return pathIn.resolve("data/" + root + "/recipes/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Caelum Apparatus";
        }
    }

    public static class ImbuementProvider extends ImbuementRecipeProvider {

        public ImbuementProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {
            for (ImbuementRecipe g : recipes) {
                Path path = getRecipePath(output, g.getId().getPath());
                saveStable(cache, g.asRecipe(), path);
            }

        }

        protected Path getRecipePath(Path pathIn, String str) {
            return pathIn.resolve("data/" + root + "/recipes/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Caelum Imbuement";
        }

    }

    public static class PatchouliProvider extends com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider {

        public PatchouliProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {

            for (AbstractRitual r : RitualRegistry.getRitualMap().values()) {
                if (r.getRegistryName().getNamespace().equals(ArsCaelum.MODID) && !(r instanceof StarterIslandRitual)) {
                    addRitualPage(r);
                }
            }

            //check the superclass for examples

            for (PatchouliPage patchouliPage : pages) {
                saveStable(cache, patchouliPage.build(), patchouliPage.path());
            }

        }

        public void addRitualPage(AbstractRitual ritual) {
            PatchouliBuilder builder = new PatchouliBuilder(RITUALS, "item." + ritual.getRegistryName().getNamespace() + "." + ritual.getRegistryName().getPath())
                    .withIcon(ritual.getRegistryName().toString())
                    .withTextPage(ritual.getDescriptionKey())
                    .withPage(new CraftingPage(ritual.getRegistryName().toString()));

            this.pages.add(new PatchouliPage(builder, this.output.resolve("assets/ars_nouveau/patchouli_books/worn_notebook/en_us/entries/rituals/" + ritual.getRegistryName().getPath() + ".json")));
        }

        /**
         * Gets a name for this provider, to use in logging.
         */
        @Override
        public String getName() {
            return "Caelum Patchouli Datagen";
        }

    }

}
