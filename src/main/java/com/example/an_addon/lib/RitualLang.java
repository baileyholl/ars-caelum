package com.example.an_addon.lib;

public class RitualLang {
    public static String COBBLE = prependRitual("cobble");
    public static String FORESTATION = prependRitual("forestation");
    public static String MANA_REGEN = prependRitual("mana_regen");
    public static String PLATFORM = prependRitual("platform");

    public static String prependRitual(String ritual) {
        return "ritual_" + ritual;
    }
}
