package com.hollingsworth.ars_caelum;

import com.hollingsworth.ars_caelum.ritual.*;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;

public class ArsNouveauRegistry {
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
}
