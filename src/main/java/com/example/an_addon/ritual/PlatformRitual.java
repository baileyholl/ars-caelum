package com.example.an_addon.ritual;

import com.example.an_addon.ExampleANAddon;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.common.block.tile.RitualBrazierTile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import software.bernie.ars_nouveau.shadowed.eliotlash.mclib.utils.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class PlatformRitual extends AbstractRitual {
    private static final int BASE_RANGE = 14;
    private static final int BASE_OFFSET = 42;
    List<BlockPos> blocks = new ArrayList<>();

    int i;
    int j;
    int k;
    @Override
    protected void tick() {
        place(i, j == 0 ? BASE_OFFSET - BASE_RANGE / 2 : j, k);
    }

    public void place(int i, int j, int k) {
        int range = 15;
        if(!getWorld().isClientSide && blocks.isEmpty()){
            Level world = getWorld();
            boolean pointy = getWorld().random.nextDouble() < 0.25;
            double heightscale = (getWorld().random.nextDouble() + 0.5) * ((double) BASE_RANGE / (double) range);

            if (j <= -BASE_RANGE * 2) {
                j = BASE_OFFSET - BASE_RANGE / 2;
            }
            if (k >= range * 2 + 1) {
                k = 0;
            }

            BlockPos pos = getPos();
            for (; i < range * 2 + 1; i++) {
                for (; j > -BASE_RANGE * 2; j--) {
                    for (; k < range * 2 + 1; k++) {
                        BlockPos pos_ = pos.offset(-range + i, -BASE_RANGE + j, -range + k);

                        if (!inRange(pos_, pos, range, heightscale, pointy)) {
                            continue;
                        }

                        BlockState state = world.getBlockState(pos_);
                        Block block = state.getBlock();

                        if (!canMove(state, world, pos_)) {
                            continue;
                        }

                        BlockEntity tile = world.getBlockEntity(pos_);
                        if(tile instanceof RitualBrazierTile)
                            continue;

                        CompoundTag cmp = new CompoundTag();
                        if (tile != null) {
                            cmp = tile.saveWithFullMetadata();
                            // Reset the block entity so e.g. chests don't spawn their drops
                            BlockEntity newTile = ((EntityBlock) block).newBlockEntity(pos_, state);
                            world.setBlockEntity(newTile);
                        }

                        // This can fail from e.g. permissions plugins or event cancellations
                        if (!world.removeBlock(pos_, false)) {
                            // put the original block entity back
                            if (tile != null) {
                                world.setBlockEntity(tile);
                            }
                            continue;
                        }
//                        world.setBlock(pos_.above(BASE_OFFSET), state, 3);

//                        world.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos_, Block.getId(state));
                        this.i = pos.getX();
                        this.j = pos.getY();
                        this.k = pos.getZ();
                        blocks.add(pos_.above(BASE_OFFSET).immutable());

//                        ItemStack copyLens = new ItemStack(this);
//                        copyLens.getOrCreateTag().putInt(TAG_LEVEL, getShardLevel(shard));
//                        copyLens.getTag().put(TAG_STATE, NbtUtils.writeBlockState(state));
//                        ItemNBTHelper.setCompound(copyLens, TAG_TILE, cmp);
//                        ItemNBTHelper.setInt(copyLens, TAG_X, pos.getX());
//                        ItemNBTHelper.setInt(copyLens, TAG_Y, pos.getY());
//                        ItemNBTHelper.setInt(copyLens, TAG_Y_START, pos_.getY());
//                        ItemNBTHelper.setInt(copyLens, TAG_Z, pos.getZ());
//                        ItemNBTHelper.setBoolean(copyLens, TAG_POINTY, pointy);
//                        ItemNBTHelper.setDouble(copyLens, TAG_HEIGHTSCALE, heightscale);
//                        ItemNBTHelper.setInt(copyLens, TAG_ITERATION_I, i);
//                        ItemNBTHelper.setInt(copyLens, TAG_ITERATION_J, j);
//                        ItemNBTHelper.setInt(copyLens, TAG_ITERATION_K, k);
//
//                        ManaBurstEntity burst = getBurst(world, pos_, copyLens);
//                        world.addFreshEntity(burst);
                        continue;
                    }
                    k = 0;
                }
                j = BASE_OFFSET - BASE_RANGE / 2;
            }
        }
        if(!getWorld().isClientSide){
            for(BlockPos pos : blocks){
                getWorld().setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
            }
            setFinished();
        }
    }


    private static boolean canMove(BlockState state, Level world, BlockPos pos) {
        FluidState fluidState = state.getFluidState();
        boolean isFlowingFluid = !fluidState.isEmpty() && !fluidState.isSource();
        Block block = state.getBlock();

        return !state.isAir()
                && !isFlowingFluid
                && !(block instanceof FallingBlock)
                && state.getDestroySpeed(world, pos) != -1;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ExampleANAddon.MODID, "platform_ritual");
    }

    private boolean inRange(BlockPos pos, BlockPos srcPos, int range, double heightscale, boolean pointy) {
        if (pos.getY() >= srcPos.getY()) {
            return pointDistanceSpace(pos.getX(), 0, pos.getZ(), srcPos.getX(), 0, srcPos.getZ()) < range;
        } else if (!pointy) {
            return pointDistanceSpace(pos.getX(), pos.getY() / heightscale, pos.getZ(), srcPos.getX(), srcPos.getY() / heightscale, srcPos.getZ()) < range;
        } else {
            return pointDistanceSpace(pos.getX(), 0, pos.getZ(), srcPos.getX(), 0, srcPos.getZ()) < range - (srcPos.getY() - pos.getY()) / heightscale;
        }
    }

    public static float pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
    }

    @Override
    public void read(CompoundTag tag) {
        super.read(tag);
        i = tag.getInt("i");
        j = tag.getInt("j");
        k = tag.getInt("k");
    }

    @Override
    public void write(CompoundTag tag) {
        super.write(tag);
        tag.putInt("i", i);
        tag.putInt("j", j);
        tag.putInt("k", k);
    }
}
