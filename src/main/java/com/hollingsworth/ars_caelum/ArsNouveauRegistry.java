package com.hollingsworth.ars_caelum;

import com.hollingsworth.ars_caelum.ritual.*;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static void registerGlyphs(){
        ArsNouveauAPI.getInstance().registerRitual(new ManaRegenRitual());
        ArsNouveauAPI.getInstance().registerRitual(new ConjurePlainsRitual());
        ArsNouveauAPI.getInstance().registerRitual(new CobbleRitual());
        ArsNouveauAPI.getInstance().registerRitual(new ForestationRitual());
        ArsNouveauAPI.getInstance().registerRitual(new StructureRitual());
    }

    public static void register(AbstractSpellPart spellPart){
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
