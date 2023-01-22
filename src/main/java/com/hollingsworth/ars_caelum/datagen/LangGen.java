package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ExampleANAddon;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.common.items.Glyph;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class LangGen extends LanguageProvider {
    public LangGen(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        ArsNouveauAPI arsNouveauAPI = ArsNouveauAPI.getInstance();
        for (Supplier<Glyph> supplier : arsNouveauAPI.getGlyphItemMap().values().stream().filter(tab -> tab.get().spellPart.getRegistryName().getNamespace().equals(ExampleANAddon.MODID)).toList()) {
            Glyph i = supplier.get();
            add("ars_caelum.glyph_desc." + i.spellPart.getRegistryName().getPath(), i.spellPart.getBookDescription());
            add("ars_caelum.glyph_name." + i.spellPart.getRegistryName().getPath(), i.spellPart.getName());
        }
        for (RitualTablet i : arsNouveauAPI.getRitualItemMap().values().stream().filter(tab -> tab.ritual.getRegistryName().getNamespace().equals(ExampleANAddon.MODID)).toList()) {
            add("ars_caelum.ritual_desc." + i.ritual.getRegistryName().getPath(), i.ritual.getLangDescription());
            add("item.ars_caelum." + i.ritual.getRegistryName().getPath(), i.ritual.getLangName());
        }

    }
}