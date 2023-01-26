package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import com.hollingsworth.arsnouveau.setup.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class RecipeData extends com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen {
    public RecipeData(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shapelessBuilder(getAddonRitual(RitualLang.COBBLE)).requires(BlockRegistry.CASCADING_LOG).requires(BlockRegistry.FROSTAYA_POD).save(consumer, new ResourceLocation(ArsCaelum.MODID, "cobble_ritual"));

        shapelessBuilder(getAddonRitual(RitualLang.PLAINS)).requires(BlockRegistry.FLOURISHING_LOG).requires(Blocks.GRASS).save(consumer, new ResourceLocation(ArsCaelum.MODID, "platform_ritual"));
        shapelessBuilder(getAddonRitual(RitualLang.FORESTATION)).requires(BlockRegistry.FLOURISHING_LOG).save(consumer, new ResourceLocation(ArsCaelum.MODID, "forestation_ritual"));
    }

    public RitualTablet getAddonRitual(String name) {
        return ArsNouveauAPI.getInstance().getRitualItemMap().get(new ResourceLocation(ArsCaelum.MODID, name));
    }
}
