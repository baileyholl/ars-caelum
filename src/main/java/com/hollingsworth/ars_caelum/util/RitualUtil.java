package com.hollingsworth.ars_caelum.util;

import net.minecraft.core.BlockPos;

public class RitualUtil {

    /**
     * Provides the next 'closest' position between two positions and an index tracking the last generated position.
     * This is for lazily getting the next closed position between two positions without generating the entire iterator.
     */
    public static BlockPos betweenClosed(int pX1, int pY1, int pZ1, int pX2, int pY2, int pZ2, int index) {
        int i = pX2 - pX1 + 1;
        int j = pY2 - pY1 + 1;
        int k = pZ2 - pZ1 + 1;
        int l = i * j * k;

        int i1 = index % i;
        int j1 = index / i;
        int k1 = j1 % j;
        int l1 = j1 / j;
        return new BlockPos(pX1 + i1, pY1 + k1, pZ1 + l1);
    }

    public static BlockPos betweenClosed(BlockPos pFirstPos, BlockPos pSecondPos, int index) {
        return betweenClosed(Math.min(pFirstPos.getX(), pSecondPos.getX()), Math.min(pFirstPos.getY(), pSecondPos.getY()), Math.min(pFirstPos.getZ(), pSecondPos.getZ()), Math.max(pFirstPos.getX(), pSecondPos.getX()), Math.max(pFirstPos.getY(), pSecondPos.getY()), Math.max(pFirstPos.getZ(), pSecondPos.getZ()), index);
    }
}
