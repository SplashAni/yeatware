package yeatware.system.modules.player;

import net.minecraft.util.math.BlockPos;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.ModeSetting;
import yeatware.utils.constants.ItemType;
import yeatware.utils.constants.modes.RotationMode;
import yeatware.utils.constants.modes.SwingMode;
import yeatware.utils.player.ItemResult;
import yeatware.utils.world.BlockUtils;

public class Scaffold extends Module {
    ModeSetting<SwingMode> swingMode = new ModeSetting<>("Swing Mode", SwingMode.Client);
    ModeSetting<RotationMode> rotationMode = new ModeSetting<>("RotationMode", RotationMode.None);

    public Scaffold() {
        super("Scaffold", Category.PLAYER);
        addSettings(swingMode, rotationMode);
    }

    ItemResult block;

    @Override
    public void onActivate() {
        block = new ItemResult(ItemType.Block);
        super.onActivate();
    }

    @Override
    public void onDeactivate() {
        super.onDeactivate();
    }

    @Override
    public void onTick() {
        if (mc.player.sidewaysSpeed == 0 && mc.player.forwardSpeed == 0) return;
        if (getPos() == null || !block.isHotbar()) return;

        BlockUtils.place(getPos(), block, swingMode.get(), rotationMode.get());


        super.onTick();
    }

    public BlockPos getPos() {
        BlockPos pos = mc.player.getBlockPos().down(1);

        if (BlockUtils.canPlace(pos)) return pos;

        BlockPos offset = pos.offset(mc.player.getMovementDirection());

        return BlockUtils.canPlace(pos) ? offset : null;
    }
}
