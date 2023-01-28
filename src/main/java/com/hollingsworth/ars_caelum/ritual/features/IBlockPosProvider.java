package com.hollingsworth.ars_caelum.ritual.features;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public interface IBlockPosProvider {

    BlockPos computeNext();

    CompoundTag serialize(CompoundTag tag);

}
