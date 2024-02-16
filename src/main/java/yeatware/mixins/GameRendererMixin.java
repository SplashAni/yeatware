package yeatware.mixins;

import me.x150.renderer.render.OutlineFramebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yeatware.system.Module;
import yeatware.system.ModuleManager;
import yeatware.system.modules.render.Chams;

import java.awt.*;

import static yeatware.Main.mc;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @SuppressWarnings("SpellCheckingInspection")
    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode = Opcodes.GETFIELD, ordinal = 0), method = "renderWorld")
    public void render(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
        if (mc.world == null || mc.gameRenderer == null) return;
        for (Module module : ModuleManager.get().getActiveModules()) module.onRender(matrices, tickDelta);
    }

    @Unique
    private boolean shouldRender = true;

    @Shadow
    protected abstract void renderHand(MatrixStack matrices, Camera camera, float tickDelta);

    @Inject(method = "renderHand", at = @At("TAIL"))
    public void render(MatrixStack matrices, Camera camera, float tickDelta, CallbackInfo ci) {

        Chams chams = ModuleManager.get().getModule(Chams.class);
        if (!chams.isActive() || chams.hands.get()) return;

        if (shouldRender) {
            shouldRender = false;
             OutlineFramebuffer.useAndDraw(() -> renderHand(matrices, camera, tickDelta), 2, Color.BLUE, new Color(68, 160, 252, 103));
            shouldRender = true;
        }
    }
}
