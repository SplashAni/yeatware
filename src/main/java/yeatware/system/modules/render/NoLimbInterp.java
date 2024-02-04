package yeatware.system.modules.render;

import yeatware.ui.settings.BooleanSetting;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.NumberSetting;

public class NoLimbInterp extends Module {
    public BooleanSetting ignoreSelf = new BooleanSetting("Ignore Self", true);
    public  NumberSetting speed = new NumberSetting("Multiplier",15,1,100);
    public NoLimbInterp() {
        super("NoLimbInterp", Category.RENDER);
        addSettings(ignoreSelf, speed);
    }

}
