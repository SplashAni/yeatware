package yeatware.system.modules.movement;

import net.minecraft.util.math.Direction;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.ui.settings.ModeSetting;

public class Sprint extends Module {
    ModeSetting<Mode> mode = new ModeSetting<>("Mode", Mode.Pressed);

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
        addSettings(mode);
    }

    @Override
    public void onTick() {
        switch (mode.get()) {
            case RageStrict, Rage -> {
                mc.player.setSprinting(true);

                if (!mode.is(Mode.RageStrict) || mc.player.getMovementSpeed() <= 0) return;
                Direction direction = mc.player.getMovementDirection();

                if (direction == Direction.DOWN || direction == Direction.UP) return;

                mc.player.setYaw(getYaw(direction));

            }
            case Pressed -> mc.options.sprintKey.setPressed(true);
        }
        super.onTick();
    }

    public float getYaw(Direction direction) {
        return switch (direction) {
            case NORTH -> 180;
            case WEST -> 90;
            case EAST -> -90;
            case SOUTH -> 0;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    @Override
    public void onDeactivate() {
        if (mode.is(Mode.Pressed)) mc.options.sprintKey.setPressed(false);
        super.onDeactivate();
    }

    public enum Mode {
        Rage,
        RageStrict,
        Pressed
    }
}
