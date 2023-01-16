package com.example.an_addon.ritual;

import com.example.an_addon.ExampleANAddon;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.capability.CapabilityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class ManaRegenRitual extends AbstractRitual {
    @Override
    protected void tick() {
        if(getWorld().isClientSide){
            ParticleUtil.spawnRitualAreaEffect(getPos(), getWorld(), getWorld().getRandom(), getCenterColor(), 5);
        }else{
            if(getWorld().getGameTime() % 40 == 0){
                getWorld().getEntitiesOfClass(Player.class, new AABB(getPos()).inflate(5)).forEach(entity -> {
                    entity.getCapability(CapabilityRegistry.MANA_CAPABILITY).ifPresent(mana -> {
                        if(mana.getCurrentMana() < mana.getMaxMana()){
                            mana.addMana(10);
                        }
                    });
                });
            }
        }
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ExampleANAddon.MODID, "mana_regen_ritual");
    }
}
