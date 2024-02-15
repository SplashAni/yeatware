package yeatware.system.modules.combat;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.ModeSetting;
import yeatware.ui.settings.NumberSetting;
import yeatware.utils.constants.RotationMode;
import yeatware.utils.constants.SwingMode;
import yeatware.utils.player.ItemResult;
import yeatware.utils.world.BlockUtils;

import java.util.ArrayList;
import java.util.List;

public class Feetrap extends Module {
    NumberSetting delay = new NumberSetting("Delay", 4, 0, 10);
    // ModeSetting<ConfirmModes> confirmMode = new ModeSetting<>("Confirm", ConfirmModes.Packet);
    ModeSetting<SwingMode> swingMode = new ModeSetting<>("Swing Mode", SwingMode.Client);

    public Feetrap() {
        super("Feetrap", Category.COMBAT);
        addSettings(delay, swingMode);
    }

    ItemResult item;

    int ticks;
    List<BlockPos> poses = new ArrayList<>();

    @Override
    public void onActivate() {
        item = new ItemResult(Items.OBSIDIAN);
        super.onActivate();
    }


    @Override
    public void onTick() {

        calculate();

        if (!item.isHotbar() || poses.isEmpty()) return;


        if (ticks > 0) {
            ticks--;
            return;
        }

        BlockPos pos = poses.get(0);

        if (BlockUtils.place(pos, item, swingMode.get(), RotationMode.Packet)) {
            poses.remove(pos);
        }
        ticks = delay.get();

        super.onTick();
    }

    public void calculate() {
        poses.clear();
        for (Direction direction : Direction.values()) {
            if (direction == Direction.DOWN || direction == Direction.UP) continue;
            BlockPos offset = mc.player.getBlockPos().offset(direction);

            if (!BlockUtils.canPlace(offset)) continue;
            poses.add(offset);
        }
    }

    @Override
    public void onRender(MatrixStack matrices) {
        super.onRender(matrices);
    }

    public enum ConfirmModes {
        Packet,
        None
    }
}
