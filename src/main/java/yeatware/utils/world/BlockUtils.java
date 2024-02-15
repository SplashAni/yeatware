package yeatware.utils.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static yeatware.Main.mc;

public class BlockUtils {
    public static Block getBlock(BlockPos pos) {
        return getState(pos).getBlock();
    }

    public static BlockState getState(BlockPos pos) {
        return mc.world.getBlockState(pos);
    }

}
