package com.hollingsworth.ars_caelum;

import com.hollingsworth.ars_caelum.ritual.*;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static void registerGlyphs(){
        ArsNouveauAPI api = ArsNouveauAPI.getInstance();;
        api.registerRitual(new ManaRegenRitual());
        api.registerRitual(new CobbleRitual());
        api.registerRitual(new BlazingIslandRitual());
        api.registerRitual(new CascadingIslandRitual());
        api.registerRitual(new FlourishingIslandRitual());
        api.registerRitual(new VexingIslandRitual());
        api.registerRitual(new GeodeIslandRitual());
        api.registerRitual(new EndRitual());
        api.registerRitual(new StarterIslandRitual());
        api.registerRitual(new SculkIslandRitual());
    }

    public static void register(AbstractSpellPart spellPart){
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
