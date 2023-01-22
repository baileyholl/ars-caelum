package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ExampleANAddon;
import com.hollingsworth.ars_caelum.lib.RitualLang;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import com.hollingsworth.arsnouveau.common.event.ManaCapEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class ManaRegenRitual extends AbstractRitual {
    @Override
    protected void tick() {
        if(getWorld().isClientSide){
            ParticleUtil.spawnRitualAreaEffect(getPos(), getWorld(), getWorld().getRandom(), getCenterColor(), 3);
        }else{
            if(getWorld().getGameTime() % 40 == 0){
                getWorld().getEntitiesOfClass(Player.class, new AABB(getPos()).inflate(3)).forEach(entity -> {
                    entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).ifPresent(mana -> {
                        if(mana.getCurrentMana() < mana.getMaxMana()){
                            mana.addMana(10);
                            ManaCapEvents.syncPlayerEvent(entity);
                        }
                    });
                });
            }
        }
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ExampleANAddon.MODID, RitualLang.MANA_REGEN);
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
