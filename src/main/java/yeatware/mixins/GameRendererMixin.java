package yeatware.mixins;

import me.x150.renderer.render.OutlineFramebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {


    @Unique
    private boolean shouldRender = true;

    @Shadow
    public abstract void renderHand(MatrixStack matrices, Camera camera, float tickDelta);

    @Inject(method = "renderHand", at = @At("TAIL"))
    public void render(MatrixStack matrices, Camera camera, float tickDelta, CallbackInfo ci) {

        if (shouldRender) {
            shouldRender = false;
            OutlineFramebuffer.useAndDraw(() -> renderHand(matrices, camera, tickDelta), 2, Color.BLUE, new Color(68, 160, 252, 103));
            shouldRender = true;
        }
    }
}
