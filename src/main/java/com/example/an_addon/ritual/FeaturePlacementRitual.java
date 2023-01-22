package com.example.an_addon.ritual;

import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public abstract class FeaturePlacementRitual extends AbstractRitual {

    public Map<String, List<BlockPos>> featureMap = new HashMap<>();

    public int checkRadius = 7;

    public int featureIndex = 0;

    public int positionIndex = 0;

    public List<BlockPos> targetPositions = new ArrayList<>();

    public List<IPlaceableFeature> features = new ArrayList<>();

    abstract void addFeatures(List<IPlaceableFeature> features);

    @Override
    public void onStart() {
        super.onStart();
        addFeatures(features);
        for(IPlaceableFeature feature : features){
            featureMap.put(feature.getFeatureName(), new ArrayList<>());
        }
        BlockPos pos = getPos();
        for(BlockPos nextPos : BlockPos.betweenClosed(getPos().offset(-checkRadius, 0, -checkRadius), getPos().offset(checkRadius, 0, checkRadius))){
            double x = nextPos.getX() + 0.5;
            double y = nextPos.getY() + 0.5;
            double z = nextPos.getZ() + 0.5;
            double dist = BlockUtil.distanceFrom(new Vec3(x, y, z), new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
            if (dist <= checkRadius) {
                targetPositions.add(nextPos.immutable());
            }
        }
        Collections.shuffle(targetPositions);
    }

    @Override
    protected void tick() {
        if(getWorld().isClientSide || getWorld().getGameTime() % 20 != 0){
            return;
        }
        for(int i = 0; i < 20; i++) {
            if (positionIndex >= targetPositions.size()) {
                featureIndex++;
                Collections.shuffle(targetPositions);
                positionIndex = 0;
            }
            if (featureIndex >= features.size()) {
                setFinished();
                return;
            }
            BlockPos targetPos = targetPositions.get(positionIndex);
            IPlaceableFeature feature = features.get(featureIndex);
            if (isEnoughBlocksFrom(feature.getFeatureName(), targetPos, feature.distanceFromOthers()) && feature.onPlace(getWorld(), targetPos, this, tile)) {
                featureMap.computeIfAbsent(feature.getFeatureName(), k -> new ArrayList<>()).add(targetPos.immutable());
                return;
            }
            positionIndex++;
        }
    }

    public boolean isEnoughBlocksFrom(String feature, BlockPos targetPos, double dist){
        if(!featureMap.containsKey(feature)){
            return true;
        }
        for(BlockPos pos : featureMap.get(feature)){
            if(BlockUtil.distanceFrom(new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5), new Vec3(targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5)) <= dist){
                return false;
            }
        }
        return true;
    }
}
