package yeatware.mixins;

import me.x150.renderer.render.OutlineFramebuffer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yeatware.system.Module;
import yeatware.system.ModuleManager;

import static yeatware.Main.mc;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        if (mc.world == null || mc.player == null) return;
        ModuleManager.get().getActiveModules().forEach(Module::onTick);
    }
}