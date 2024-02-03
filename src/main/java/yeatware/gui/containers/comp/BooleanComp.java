package yeatware.gui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.gui.settings.BooleanSetting;
import yeatware.gui.settings.Setting;

import java.awt.*;

import static yeatware.Main.mc;

public class BooleanComp extends Componenet {
    BooleanSetting booleanSetting;
    int boxWidth, boxHeight;

    public BooleanComp(Setting setting, int x, int y) {
        super(setting, x, y);
        booleanSetting = (BooleanSetting) setting;

        boxWidth = 10;
        boxHeight = 9;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x, y, x + boxWidth, y + boxHeight, new Color(71, 71, 166,255).getRGB());

        if (booleanSetting.get()) {
            context.fill(
                    x + (boxWidth - 6) / 2,
                    y + (boxHeight - 6) / 2,
                    x + (boxWidth + 6) / 2,
                    y + (boxHeight + 6) / 2,
                    new Color(53, 73, 98,255).getRGB()
            );
        }

        context.drawText(mc.textRenderer, setting.getName(), x + boxWidth + 3, y, -1, true);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && isHovered(mouseX, mouseY)) booleanSetting.toggle();
        super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + boxWidth && mouseY >= y && mouseY <= y + boxHeight;
    }
}
