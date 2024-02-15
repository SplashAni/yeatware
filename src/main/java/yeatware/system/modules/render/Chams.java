package yeatware.system.modules.render;

import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.BooleanSetting;

public class Chams extends Module {
    public BooleanSetting hands = new BooleanSetting("Hands", false);

    public Chams() {
        super("Chams", Category.RENDER);
        addSettings(hands);
    }
}
