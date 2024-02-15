package yeatware.system.modules.client;

import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.ModeSetting;
import yeatware.utils.constants.TimeMode;

public class InteractionModule extends Module { // good idea BO2 (':
    public ModeSetting<TimeMode> swing = new ModeSetting<>("Swing", TimeMode.Post);

    public ModeSetting<TimeMode> rotate = new ModeSetting<>("Rotate", TimeMode.Post);


    public InteractionModule() {
        super("Interaction", Category.CLIENT);
        addSettings(swing, rotate);
    }

}
