package yeatware.system.modules.render;

import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.BooleanSetting;
import yeatware.ui.settings.ModeSetting;
import yeatware.ui.settings.NumberSetting;

public class NoLimbInterp extends Module {
    public BooleanSetting ignoreSelf = new BooleanSetting("Ignore Self", true);
    public NumberSetting speed = new NumberSetting("Multiplier", 15, 1, 100);
    ModeSetting<modez> modeSetting = new ModeSetting<>("Modes", modez.God);

    public NoLimbInterp() {
        super("NoLimbInterp", Category.RENDER);
        addSettings(ignoreSelf, speed, modeSetting);
    }

    public enum modez {
        Test,
        God,
        CC
    }
}
