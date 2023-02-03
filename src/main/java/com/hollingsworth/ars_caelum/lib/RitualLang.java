package com.hollingsworth.ars_caelum.lib;

public class RitualLang {
    public static String COBBLE = prependRitual("sedimentation");
    public static String MANA_REGEN = prependRitual("mana_regeneration");
    public static String BLAZING = prependRitual("conjure_island_blazing");
    public static String GEODE = prependRitual("conjure_island_geode");
    public static String FLOURISHING = prependRitual("conjure_island_flourishing");
    public static String VEXING = prependRitual("conjure_island_vexing");
    public static String CASCADING = prependRitual("conjure_island_cascading");
    public static String END_PORTAL = prependRitual("conjure_island_end_portal");
    public static String STARTER = prependRitual("conjure_island_starter");
    public static String SCULK = prependRitual("conjure_island_sculk");
    public static String ELDER_SUMMON = prependRitual("elder_summon");


    public static String prependRitual(String ritual) {
        return "ritual_" + ritual;
    }
}
