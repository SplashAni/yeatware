package yeatware.gui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.gui.settings.BooleanSetting;
import yeatware.gui.settings.Setting;

import java.awt.*;

import static yeatware.Main.mc;

public class BooleanComp extends Componenet {
    BooleanSetting booleanSetting;

    public BooleanComp(Setting setting, int x, int y) {
        super(setting, x, y);
        booleanSetting = (BooleanSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x, y, x + 30, y + 5, Color.BLUE.getRGB());
        context.drawText(mc.textRenderer, setting.getName().concat(" ".concat(String.valueOf(booleanSetting.getValue()))), x, y, -1, true);
        super.render(context, mouseX, mouseY, delta);
    }
}
