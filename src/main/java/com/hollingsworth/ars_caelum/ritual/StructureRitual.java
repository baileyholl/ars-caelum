package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.ars_caelum.ArsCaelum;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;

public class StructureRitual extends AbstractRitual {
    @Override
    protected void tick() {
        if(getWorld().isClientSide)
            return;
        StructureTemplateManager manager = getWorld().getServer().getStructureManager();
        StructureTemplate structureTemplate = manager.getOrCreate(new ResourceLocation(ArsCaelum.MODID, "test"));
        List<StructureTemplate.StructureBlockInfo> infoList = structureTemplate.palettes.get(0).blocks();
        BlockPos pos = getPos();
        for(StructureTemplate.StructureBlockInfo blockInfo : infoList){
            BlockPos translatedPos = getPos().offset(blockInfo.pos.getX(), blockInfo.pos.getY(), blockInfo.pos.getZ());
            getWorld().setBlock(translatedPos, blockInfo.state, 2);
        }
        setFinished();

    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ArsCaelum.MODID, "structure_ritual");
    }
}
