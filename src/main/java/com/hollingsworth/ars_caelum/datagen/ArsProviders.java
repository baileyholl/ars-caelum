package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.ritual.StarterIslandRitual;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
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
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.hollingsworth.arsnouveau.api.RegistryHelper.getRegistryName;

public class ArsProviders {

    static String root = ArsCaelum.MODID;

    public static class CrushProvider extends CrushRecipeProvider{
        public DataGenerator generator;
        public List<CrushRecipe> replaceAn = new ArrayList<>();
        public CrushProvider(DataGenerator generatorIn) {
            super(generatorIn);
            this.generator = generatorIn;
        }

        @Override
        public void run(CachedOutput cache) throws IOException {
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
            Path output = this.generator.getOutputFolder();
            for (CrushRecipe g : recipes) {
                Path path = getRecipePath(output, g.getId().getPath());
                DataProvider.saveStable(cache, g.asRecipe(), path);
            }

            for (CrushRecipe g : replaceAn) {
                Path path = getANPath(output, g.getId().getPath());
                DataProvider.saveStable(cache, g.asRecipe(), path);
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
        public void run(CachedOutput cache) throws IOException {

            Path output = this.generator.getOutputFolder();

//            recipes.add(get(TestEffect.INSTANCE).withItem(Items.DIRT));

            for (GlyphRecipe recipe : recipes) {
                Path path = getScribeGlyphPath(output, recipe.output.getItem());
                DataProvider.saveStable(cache, recipe.asRecipe(), path);
            }

        }
        protected static Path getScribeGlyphPath(Path pathIn, Item glyph) {
            return pathIn.resolve("data/" + root + "/recipes/" + getRegistryName(glyph).getPath() + ".json");
        }

        @Override
        public String getName() {
            return "Example Glyph Recipes";
        }
    }

    public static class EnchantingAppProvider extends ApparatusRecipeProvider {

        public EnchantingAppProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void run(CachedOutput cache) throws IOException {

            recipes.add(builder()
                    .withReagent(Ingredient.of(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                    .withPedestalItem(8, Items.NAUTILUS_SHELL)
                    .withResult(Items.HEART_OF_THE_SEA)
                    .withSourceCost(10000)
                    .build()
            );


            Path output = this.generator.getOutputFolder();
            for (EnchantingApparatusRecipe g : recipes){
                if (g != null){
                    Path path = getRecipePath(output, g.getId().getPath());
                    DataProvider.saveStable(cache, g.asRecipe(), path);
                }
            }

        }

        protected static Path getRecipePath(Path pathIn, String str){
            return pathIn.resolve("data/"+ root +"/recipes/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Example Apparatus";
        }
    }

    public static class ImbuementProvider extends ImbuementRecipeProvider {

        public ImbuementProvider(DataGenerator generatorIn){
            super(generatorIn);
        }

        @Override
        public void run(CachedOutput cache) throws IOException {

            /*
            recipes.add(new ImbuementRecipe("example_focus", Ingredient.of(Items.AMETHYST_SHARD), new ItemStack(ItemsRegistry.SUMMONING_FOCUS, 1), 5000)
                    .withPedestalItem(ItemsRegistry.WILDEN_TRIBUTE)
            );
            */

            Path output = generator.getOutputFolder();
            for(ImbuementRecipe g : recipes){
                Path path = getRecipePath(output, g.getId().getPath());
                DataProvider.saveStable(cache, g.asRecipe(), path);
            }

        }

        protected Path getRecipePath(Path pathIn, String str){
            return pathIn.resolve("data/"+ root +"/recipes/" + str + ".json");
        }

        @Override
        public String getName() {
            return "Example Imbuement";
        }

    }

    public static class PatchouliProvider extends com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider {

        public PatchouliProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void run(CachedOutput cache) throws IOException {

            for (AbstractRitual r : ArsNouveauAPI.getInstance().getRitualMap().values()) {
                if(r.getRegistryName().getNamespace().equals(ArsCaelum.MODID) && !(r instanceof StarterIslandRitual)) {
                    addRitualPage(r);
                }
            }

            //check the superclass for examples

            for (PatchouliPage patchouliPage : pages) {
                DataProvider.saveStable(cache, patchouliPage.build(), patchouliPage.path());
            }

        }

        public void addRitualPage(AbstractRitual ritual) {
            PatchouliBuilder builder = new PatchouliBuilder(RITUALS, "item." + ritual.getRegistryName().getNamespace() + "." + ritual.getRegistryName().getPath())
                    .withIcon(ritual.getRegistryName().toString())
                    .withTextPage(ritual.getDescriptionKey())
                    .withPage(new CraftingPage(ritual.getRegistryName().toString()));

            this.pages.add(new PatchouliPage(builder, this.generator.getOutputFolder().resolve("data/" + ritual.getRegistryName().getNamespace() + "/patchouli_books/caelum_notes/en_us/entries/rituals/" + ritual.getRegistryName().getPath() + ".json")));
        }


        /**
         * Gets a name for this provider, to use in logging.
         */
        @Override
        public String getName() {
            return "Example Patchouli Datagen";
        }

        @Override
        public Path getPath(ResourceLocation category, String fileName) {
            return this.generator.getOutputFolder().resolve("data/"+ root +"/patchouli_books/example/en_us/entries/" + category.getPath() + "/" + fileName + ".json");
        }
    }

}
