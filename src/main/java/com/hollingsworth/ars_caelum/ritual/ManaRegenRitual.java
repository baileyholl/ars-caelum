package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.potions.ModPotions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class ManaRegenRitual extends AbstractRitual {
    @Override
    protected void tick() {
        if(getWorld().isClientSide){
            ParticleUtil.spawnRitualAreaEffect(getPos(), getWorld(), getWorld().getRandom(), getCenterColor(), 3);
        }else{
            if(getWorld().getGameTime() % 40 == 0){
                getWorld().getEntitiesOfClass(Player.class, new AABB(getPos()).inflate(5)).forEach(entity -> {
                    entity.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT.get(), 20 * 10));
                });
            }
        }
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, RitualLang.MANA_REGEN);
    }

    @Override
    public String getName() {
        return "Mana Regeneration";
    }

    @Override
    public String getLangDescription() {
        return "Grants a small amount of mana to all players nearby.";
    }
}
