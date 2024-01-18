package com.hollingsworth.ars_caelum;

import com.hollingsworth.ars_caelum.ritual.*;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static void registerGlyphs(){
        RitualRegistry.registerRitual(new ManaRegenRitual());
        RitualRegistry.registerRitual(new CobbleRitual());
        RitualRegistry.registerRitual(new BlazingIslandRitual());
        RitualRegistry.registerRitual(new CascadingIslandRitual());
        RitualRegistry.registerRitual(new FlourishingIslandRitual());
        RitualRegistry.registerRitual(new VexingIslandRitual());
        RitualRegistry.registerRitual(new GeodeIslandRitual());
        RitualRegistry.registerRitual(new EndRitual());
        RitualRegistry.registerRitual(new StarterIslandRitual());
        RitualRegistry.registerRitual(new SculkIslandRitual());
        RitualRegistry.registerRitual(new ElderSummonRitual());
        RitualRegistry.registerRitual(new VillageIslandRitual());
    }

    public static void register(AbstractSpellPart spellPart){
        GlyphRegistry.registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

}
