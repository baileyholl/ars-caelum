package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.common.entity.WildenChimera;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ElderSummonRitual extends AbstractRitual {
    @Override
    protected void tick() {
        WildenChimera.spawnPhaseParticles(getPos().above(), getWorld(), 1);
        if (getWorld().getGameTime() % 20 == 0)
            incrementProgress();
        if (getWorld().getGameTime() % 60 == 0 && !getWorld().isClientSide) {
            if (!isBossSpawn()) {
                BlockPos summonPos = getPos().above().east(rand.nextInt(3) - rand.nextInt(6)).north(rand.nextInt(3) - rand.nextInt(6));
                Mob mobEntity = new Guardian(EntityType.GUARDIAN, getWorld());
                summon(mobEntity, summonPos);
                if (getProgress() >= 15) {
                    setFinished();
                }
            } else {
                if (getProgress() >= 8) {
                    ElderGuardian chimera = new ElderGuardian(EntityType.ELDER_GUARDIAN, getWorld());
                    summon(chimera, getPos().above());
                    setFinished();
                }
            }
        }
    }

    public boolean isBossSpawn() {
        return didConsumeItem(Items.SEA_LANTERN);
    }

    public void summon(Mob mob, BlockPos pos) {
        mob.setPos(pos.getX(), pos.getY(), pos.getZ());
        mob.level().addFreshEntity(mob);
    }

    @Override
    public boolean canConsumeItem(ItemStack stack) {
        return super.canConsumeItem(stack) || (!isBossSpawn() && stack.getItem() == Items.SEA_LANTERN);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return ArsCaelum.prefix(RitualLang.ELDER_SUMMON);
    }

    @Override
    public String getLangDescription() {
        return "Summons waves of Guardians. Can be augmented with a Sea Lantern to summon an Elder Guardian.";
    }

    @Override
    public String getLangName() {
        return "Summon Guardians";
    }
}
