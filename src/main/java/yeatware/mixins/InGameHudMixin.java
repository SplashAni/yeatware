package yeatware.mixins;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yeatware.utils.BetaUtils;

import java.awt.*;

import static yeatware.Main.mc;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("HEAD"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        String txt = BetaUtils.beta ?  "YeatWare Beta" : "YeatWare 1.0";
        context.drawText(mc.textRenderer, txt, 1, 1, new Color(52, 151, 164, 255).getRGB(), false);
    }
}
