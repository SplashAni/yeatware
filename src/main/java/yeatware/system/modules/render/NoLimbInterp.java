package yeatware.system.modules.render;

import yeatware.gui.settings.BooleanSetting;
import yeatware.system.Category;
import yeatware.system.Module;

public class NoLimbInterp extends Module {
    BooleanSetting ignoreSelf = new BooleanSetting("Ignore Self", true);

    public NoLimbInterp() {
        super("NoLimbInterp", Category.RENDER);
        addSettings(ignoreSelf);
    }

}
