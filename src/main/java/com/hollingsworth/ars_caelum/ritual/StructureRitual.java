package com.hollingsworth.ars_caelum.ritual;

import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;
import java.util.List;

public abstract class StructureRitual extends AbstractRitual {
    public ResourceLocation structure;
    public BlockPos offset;
    List<StructureTemplate.StructureBlockInfo> blocks = new ArrayList<>();
    int index;
    int sourceRequired;
    boolean hasConsumed;

    public StructureRitual(ResourceLocation structure, BlockPos offset, int sourceRequired){
        this.structure = structure;
        this.offset = offset;
        this.sourceRequired = sourceRequired;
        this.hasConsumed = sourceRequired == 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getWorld().isClientSide)
            return;
        if(!hasConsumed){
            setNeedsSource(true);
        }
        StructureTemplateManager manager = getWorld().getServer().getStructureManager();
        StructureTemplate structureTemplate = manager.getOrCreate(structure);
        List<StructureTemplate.StructureBlockInfo> infoList = structureTemplate.palettes.get(0).blocks();
        blocks = new ArrayList<>(infoList.stream().filter(b -> !b.state.isAir()).toList());
        blocks.sort(new StructureComparator(getPos(), offset));
    }

    @Override
    protected void tick() {
        if(getWorld().isClientSide)
            return;
        int placeCount = 0;
        while(placeCount < 5){
            if (index >= blocks.size()) {
                setFinished();
                return;
            }
            StructureTemplate.StructureBlockInfo blockInfo = blocks.get(index);
            BlockPos translatedPos = getPos().offset(blockInfo.pos.getX(), blockInfo.pos.getY(), blockInfo.pos.getZ()).offset(offset);
            if (getWorld().getBlockState(translatedPos).getMaterial().isReplaceable()) {
                getWorld().setBlock(translatedPos, blockInfo.state, 2);
                placeCount++;
            }
            index++;
        }
    }

    @Override
    public void read(CompoundTag tag) {
        super.read(tag);
        index = tag.getInt("index");
        hasConsumed = tag.getBoolean("hasConsumed");

    }

    @Override
    public void write(CompoundTag tag) {
        super.write(tag);
        tag.putInt("index", index);
        tag.putBoolean("hasConsumed", hasConsumed);
    }

    @Override
    public int getSourceCost() {
        return sourceRequired;
    }

    @Override
    public abstract ResourceLocation getRegistryName();
}
