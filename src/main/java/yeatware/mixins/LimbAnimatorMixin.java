package yeatware.mixins;

import net.minecraft.entity.LimbAnimator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yeatware.system.ModuleManager;
import yeatware.system.modules.render.NoLimbInterp;

@Mixin(LimbAnimator.class)
public class LimbAnimatorMixin {
    @Inject(method = "updateLimbs", at = @At("HEAD"), cancellable = true)
    public void updateLimbs(float speed, float multiplier, CallbackInfo ci) {
        if (ModuleManager.get().getModule(NoLimbInterp.class).isActive()) ci.cancel();
    }
}
