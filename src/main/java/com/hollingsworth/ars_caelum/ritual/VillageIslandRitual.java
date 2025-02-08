package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.StructureRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class VillageIslandRitual extends StructureRitual {
    public VillageIslandRitual() {
        super(ArsCaelum.prefix("village_2"), new BlockPos(-13, -5, -13), 10000, null);
    }

    @Override
    public String getLangName() {
        return "Conjure Island: Village";
    }

    @Override
    public String getLangDescription() {
        return "Conjures an archwood village island with several villagers.";
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.VILLAGE);
    }
}
