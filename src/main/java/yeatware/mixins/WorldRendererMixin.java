package yeatware.mixins;

import me.x150.renderer.render.OutlineFramebuffer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Shadow
    protected abstract void renderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers);

    @Unique
    private boolean shouldRender = true;

    @Inject(method = "renderEntity", at = @At("TAIL"))

    public void renderEntity1(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {

        if (shouldRender) {
            shouldRender = false;
            OutlineFramebuffer.useAndDraw(() -> renderEntity(entity, cameraX, cameraY, cameraZ, tickDelta, matrices, vertexConsumers), 1, Color.BLUE, new Color(68, 160, 252, 103));
            shouldRender = true;
        }
    }
}
