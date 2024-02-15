package yeatware.system.modules.combat;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.ModeSetting;
import yeatware.ui.settings.NumberSetting;
import yeatware.utils.player.ItemResult;

public class Feetrap extends Module {
    NumberSetting delay = new NumberSetting("Delay", 4, 0, 10);
    ModeSetting<ConfirmModes> confirmMode = new ModeSetting<>("Confirm", ConfirmModes.Packet);

    public Feetrap() {
        super("Feetrap", Category.COMBAT);
    }

    ItemResult item;

    int ticks;

    @Override
    public void onActivate() {
        item = new ItemResult(Items.OBSIDIAN);
        super.onActivate();
    }

    @Override
    public void onTick() {

        if (!item.isHotbar()) return;

        if (ticks > 0) {
            ticks--;
            return;
        }



        ticks = delay.get();
        super.onTick();
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
