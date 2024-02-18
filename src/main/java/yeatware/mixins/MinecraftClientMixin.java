package yeatware.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yeatware.Main;
import yeatware.event.events.TickEvent;
import yeatware.system.Module;
import yeatware.system.ModuleManager;
import yeatware.system.modules.world.FastUse;

import static yeatware.Main.mc;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        if (mc.world == null || mc.player == null) return;
        Main.BUS.post(new TickEvent());
        ModuleManager.get().getActiveModules().forEach(Module::onTick);
    }

    @ModifyConstant(method = "doItemUse", constant = @Constant(intValue = 4)) // mixins are so fun
    private int doItemUse(int i) {
        FastUse fastUse = ModuleManager.get().getModule(FastUse.class);

        if (!fastUse.isActive()) return i;

        return fastUse.delay.get();
    }
}