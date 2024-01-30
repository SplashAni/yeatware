package yeatware.mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yeatware.system.ModuleManager;
import yeatware.system.modules.render.ForceSneak;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "isInSneakingPose", at = @At("RETURN"), cancellable = true)
    public void sneak(CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.get().getModule(ForceSneak.class).isActive()) cir.setReturnValue(true);
    }
}
