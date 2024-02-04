package yeatware.ui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.ui.settings.NumberSetting;
import yeatware.ui.settings.Setting;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static yeatware.Main.mc;

public class NumberComp extends Componenet {
    NumberSetting numberSetting;
    int maxLength;
    int lineLength = 50;
    int boxWidth = 7;
    int boxHeight = 7;
    boolean sliding;

    public NumberComp(Setting setting, int x, int y, int maxLength) {
        super(setting, x, y);
        this.maxLength = maxLength;
        this.numberSetting = (NumberSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawText(mc.textRenderer, setting.getName(), x, y, -1, true);

        context.fill(x + maxLength, y + boxHeight / 2, x + maxLength + lineLength, y + boxHeight / 2 + 1, new Color(18, 18, 52, 255).getRGB());

        if (sliding) {
            int calculatedValue = calculateValue(mouseX);
            numberSetting.setValue(calculatedValue);
        }

        int squareX = x + maxLength + (int) ((float) numberSetting.get() / (float) numberSetting.getMax() * lineLength) - boxWidth / 2;
        int squareY = y;

        context.fill(squareX, squareY, squareX + boxWidth, squareY + boxHeight, new Color(53, 73, 98, 255).getRGB());
        context.drawBorder(squareX, squareY, boxWidth, boxHeight, new Color(38, 38, 61, 255).getRGB());
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY) && button == 0) {
            sliding = true;
            numberSetting.setValue(calculateValue(mouseX));
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    private int calculateValue(double mouseX) {
        int maxX = x + maxLength + lineLength - boxWidth;
        int relativeX = (int) (mouseX - x - maxLength);

        relativeX = Math.max(0, Math.min(relativeX, maxX));

        double percentage = (double) relativeX / (double) maxX;
        double sliderValue = percentage * numberSetting.getMax();

        return (int) sliderValue;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            sliding = false;
        }
        super.mouseReleased(mouseX, mouseY, button);
    }

    private boolean isHovered(int mouseX, int mouseY) {
        int squareX = x + maxLength + (int) ((float) numberSetting.get() / (float) numberSetting.getMax() * lineLength) - boxWidth / 2;
        int squareY = y;

        return mouseX >= squareX && mouseX <= squareX + boxWidth && mouseY >= squareY && mouseY <= squareY + boxHeight;
    }
}
