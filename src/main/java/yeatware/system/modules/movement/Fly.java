package yeatware.system.modules.movement;

import yeatware.system.Category;
import yeatware.system.Module;

public class Fly extends Module {
    public Fly() {
        super("Fly", Category.MOVEMENT);
    }

    @Override
    public void onActivate() {

        if (!mc.player.isSpectator() || !mc.player.isCreative()) {
            mc.player.getAbilities().allowFlying = true;
        }

        super.onActivate();
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void onDeactivate() {
        mc.player.getAbilities().allowFlying = false;
        super.onDeactivate();
    }

}
