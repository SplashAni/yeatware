package yeatware.utils.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import yeatware.utils.constants.modes.RotationMode;
import yeatware.utils.constants.modes.SwingMode;
import yeatware.utils.player.ItemResult;
import yeatware.utils.player.PlayerUtils;

import static yeatware.Main.mc;

public class BlockUtils {


    public static boolean place(BlockPos pos, ItemResult item, SwingMode swingMode, boolean swapBack, RotationMode rotationMode, boolean checkEntities) {
        if (pos == null) return false;
        if (!canPlace(pos, checkEntities)) return false;
        if (!item.isHotbar()) return false;


        if (PlayerUtils.preSwing()) PlayerUtils.swingHand(swingMode, item.getHand());

        item.swap();

        BlockHitResult bhr = new BlockHitResult(pos.toCenterPos(), Direction.DOWN, pos, true); //todo SOME BYPAZZES?

        mc.getNetworkHandler().sendPacket(new PlayerInteractBlockC2SPacket(item.getHand(), bhr, 0));

        if (!PlayerUtils.preSwing()) PlayerUtils.swingHand(swingMode, item.getHand());

        if (swapBack) item.swapBack();

        return true;
    }

    public static boolean place(BlockPos pos, ItemResult item, SwingMode swingMode, RotationMode rotationMode) {
        return place(pos, item, swingMode, true, rotationMode, true);
    }

    public static boolean canPlace(BlockPos pos) {
        return canPlace(pos, true);
    }

    public static boolean canPlace(BlockPos pos, boolean entities) {
        if (!getState(pos).isReplaceable()) return false;

        if (entities) for (Entity entity : mc.world.getEntities())
            if (entity.getBoundingBox().intersects(new Box(pos))) return false;

        return true;
    }

    public static Block getBlock(BlockPos pos) {
        return getState(pos).getBlock();
    }

    public static BlockState getState(BlockPos pos) {
        return mc.world.getBlockState(pos);
    }
}
