package yeatware.event.events;

import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import yeatware.event.Event;

public class PlayerMoveEvent extends Event {
    MovementType movementType;
    Vec3d vec3d;

    public PlayerMoveEvent(MovementType movementType, Vec3d vec3d) {
        this.movementType = movementType;
        this.vec3d = vec3d;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public Vec3d getVec3d() {
        return vec3d;
    }
}
