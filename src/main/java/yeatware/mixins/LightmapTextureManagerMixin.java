package yeatware.mixins;

import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import yeatware.system.ModuleManager;
import yeatware.system.modules.render.FullBright;

import static yeatware.Main.mc;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Ljava/lang/Double;floatValue()F"))
    public float update(Double instance) {
        return ModuleManager.get().getModule(FullBright.class).isActive() ? 100f : mc.options.getGamma().getValue().floatValue();
    }
}
