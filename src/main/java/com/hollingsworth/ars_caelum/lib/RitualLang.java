package com.hollingsworth.ars_caelum.lib;

public class RitualLang {
    public static String COBBLE = prependRitual("cobble");
    public static String FORESTATION = prependRitual("forestation");
    public static String MANA_REGEN = prependRitual("mana_regen");
    public static String PLAINS = prependRitual("conjure_plains");
    public static String BLAZING = prependRitual("blazing_island");
    public static String GEODE = prependRitual("geode_island");
    public static String FLOURISHING = prependRitual("flourishing_island");
    public static String VEXING = prependRitual("vexing_island");
    public static String CASCADING = prependRitual("cascading_island");
    public static String FLOWERING = prependRitual("flowering");

    public static String prependRitual(String ritual) {
        return "ritual_" + ritual;
    }
}
