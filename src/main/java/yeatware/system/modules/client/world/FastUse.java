package yeatware.system.modules.client.world;

import net.minecraft.item.BlockItem;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.BooleanSetting;
import yeatware.ui.settings.NumberSetting;

public class FastUse extends Module {
    public NumberSetting delay = new NumberSetting("Delay", 0, 0, 4);

    public FastUse() {
        super("FastUse", Category.WORLD);
        addSettings(delay);
    }


}
