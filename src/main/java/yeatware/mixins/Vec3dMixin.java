package yeatware.mixins;

import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import yeatware.utils.constants.IVec3d;

@Mixin(Vec3d.class)
public class Vec3dMixin implements IVec3d {
    @Mutable
    @Shadow
    @Final
    public double x;
    @Mutable
    @Shadow
    @Final
    public double y;
    @Mutable
    @Shadow
    @Final
    public double z;

    @Override
    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
