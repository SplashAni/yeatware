package yeatware.system.modules.combat;

import yeatware.system.Category;
import yeatware.system.Module;

public class TriggerBot extends Module {
    public TriggerBot() {
        super("TriggerBot", Category.COMBAT);
    }

    @Override
    public void onTick() {
        System.out.println("ok");
        super.onTick();
    }
}
