package com.example.an_addon;

import com.example.an_addon.glyphs.TestEffect;
import com.example.an_addon.ritual.ManaRegenRitual;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static void registerGlyphs(){
        register(TestEffect.INSTANCE);
    }

    public static void register(AbstractSpellPart spellPart){
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
        ArsNouveauAPI.getInstance().registerRitual(new ManaRegenRitual());
    }

}
