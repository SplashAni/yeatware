package yeatware.system.modules.render;

import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.NumberSetting;

public class FullBright extends Module {
    public NumberSetting multiplier = new NumberSetting("Multiplier", 10, 1, 10);

    public FullBright() {
        super("Fullbright", Category.RENDER);
        addSettings(multiplier);
    }
}
