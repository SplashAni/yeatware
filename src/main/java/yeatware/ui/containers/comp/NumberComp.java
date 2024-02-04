package yeatware.ui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.ui.settings.NumberSetting;
import yeatware.ui.settings.Setting;

import java.awt.*;

import static yeatware.Main.mc;

public class NumberComp extends Componenet {
    NumberSetting numberSetting;
    int maxLength;
    int lineLength = 20;
    int boxWidth = 7;
    int boxHeight = 7;
    boolean sliding;

    public NumberComp(Setting setting, int x, int y, int maxLength) {
        super(setting, x, y);
        this.maxLength = maxLength;
        numberSetting = (NumberSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawText(mc.textRenderer, setting.getName(), x, y, -1, true);

        context.fill(x + maxLength, y + boxHeight / 2, x + maxLength + lineLength, y + boxHeight / 2 + 1, Color.WHITE.getRGB());

        if (sliding) {
            numberSetting.setValue(calculateValue(mouseX));
        }

        int squareX = x + maxLength + (int) ((float) numberSetting.get() / (float) numberSetting.getMax() * lineLength) - boxWidth / 2;
        int squareY = y;

        context.fill(squareX, squareY, squareX + boxWidth, squareY + boxHeight, Color.RED.getRGB());

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY, x + maxLength, y, boxWidth, boxHeight) && button == 0) {
            sliding = true;
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            sliding = false;
        }
        super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean isHovered(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    private int calculateValue(double mouseX) {
        int maxX = x + maxLength + lineLength - boxWidth;
        int relativeX = (int) (mouseX - x - maxLength - boxWidth / 2);
        relativeX = Math.max(0, Math.min(relativeX, maxX));

        return (int) (((float) relativeX / (float) maxX) * numberSetting.getMax());
    }
}
