package yeatware.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yeatware.Main;
import yeatware.event.events.PlayerMoveEvent;
import yeatware.system.ModuleManager;
import yeatware.system.modules.render.ForceSneak;

import static yeatware.Main.mc;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public abstract int getId();

    @Shadow
    public abstract Text getName();

    @Inject(method = "isInSneakingPose", at = @At("RETURN"), cancellable = true)
    public void sneak(CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.get().getModule(ForceSneak.class).isActive()) cir.setReturnValue(true);
    }

    @Inject(method = "move", at = @At("HEAD"))
    private void move(MovementType type, Vec3d movement, CallbackInfo info) {
        if (getName() == mc.player.getName())
            if (Main.BUS.post(new PlayerMoveEvent(type, movement).isCancelled())) info.cancel();
    }
}
