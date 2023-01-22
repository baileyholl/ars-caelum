package com.example.an_addon;

import com.example.an_addon.ritual.CobbleRitual;
import com.example.an_addon.ritual.ManaRegenRitual;
import com.example.an_addon.ritual.PlatformRitual;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static void registerGlyphs(){
        ArsNouveauAPI.getInstance().registerRitual(new ManaRegenRitual());
        ArsNouveauAPI.getInstance().registerRitual(new PlatformRitual());
        ArsNouveauAPI.getInstance().registerRitual(new CobbleRitual());
    }

    public static void register(AbstractSpellPart spellPart){
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
