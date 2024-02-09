package yeatware.ui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.ui.settings.BooleanSetting;
import yeatware.ui.settings.Setting;

import java.awt.*;

import static yeatware.Main.mc;

public class BooleanComp extends Componenet {
    private final BooleanSetting booleanSetting;
    private final int boxWidth, boxHeight, maxLength;

    public BooleanComp(Setting setting, int x, int y, int maxLength) {
        super(setting, x, y);
        booleanSetting = (BooleanSetting) setting;
        this.maxLength = maxLength;

        boxWidth = 5;
        boxHeight = 5;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawText(mc.textRenderer, setting.getName(), x, y, -1, true);

        int boxX = x + maxLength;

        context.fill(boxX, y, boxX + boxWidth, y + boxHeight, new Color(32, 32, 40, 255).getRGB());

        if (booleanSetting.get()) {
            int innerBoxSize = 6;
            int innerBoxX = boxX + (boxWidth - innerBoxSize) / 2;
            int innerBoxY = y + (boxHeight - innerBoxSize) / 2;

            context.fill(innerBoxX, innerBoxY, innerBoxX + innerBoxSize, innerBoxY + innerBoxSize, new Color(53, 73, 98, 255).getRGB());
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && isHovered(mouseX, mouseY)) booleanSetting.toggle();
        super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x + maxLength && mouseX <= x + maxLength + boxWidth
                && mouseY >= y && mouseY <= y + boxHeight;
    }
}
