package com.hollingsworth.ars_caelum.datagen;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.items.Glyph;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class LangGen extends LanguageProvider {
    public LangGen(PackOutput pack, String modid, String locale) {
        super(pack, modid, locale);
    }

    @Override
    protected void addTranslations() {
        for (Supplier<Glyph> supplier : GlyphRegistry.getGlyphItemMap().values().stream().filter(tab -> tab.get().spellPart.getRegistryName().getNamespace().equals(ArsCaelum.MODID)).toList()) {
            Glyph i = supplier.get();
            add("ars_caelum.glyph_desc." + i.spellPart.getRegistryName().getPath(), i.spellPart.getBookDescription());
            add("ars_caelum.glyph_name." + i.spellPart.getRegistryName().getPath(), i.spellPart.getName());
        }
        for (RitualTablet i : RitualRegistry.getRitualItemMap().values().stream().filter(tab -> tab.ritual.getRegistryName().getNamespace().equals(ArsCaelum.MODID)).toList()) {
            add("ars_caelum.ritual_desc." + i.ritual.getRegistryName().getPath(), i.ritual.getLangDescription());
            add("item.ars_caelum." + i.ritual.getRegistryName().getPath(), i.ritual.getLangName());
        }

        add( "ars_caelum.tablet_of", "Tablet of %s");

    }
}