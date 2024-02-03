package yeatware.system.modules.render;

import yeatware.ui.settings.BooleanSetting;
import yeatware.system.Category;
import yeatware.system.Module;

public class ForceSneak extends Module {
    BooleanSetting ignoreSelf = new BooleanSetting("Funny", true);
    BooleanSetting test = new BooleanSetting("Nah", true);

    public ForceSneak() {
        super("ForceSneak", Category.RENDER);
        addSettings(ignoreSelf, test);
    }

}
