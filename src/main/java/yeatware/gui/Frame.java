package yeatware.gui;

import net.minecraft.client.gui.DrawContext;
import yeatware.system.Category;

import java.awt.*;

import static yeatware.Main.mc;

public class Frame {
    GuiScreen guiScreen;
    Category category;
    int width, height;
    int x, y;

    public Frame(GuiScreen guiScreen, Category category, int width, int height, int x, int y) {
        this.guiScreen = guiScreen;
        this.category = category;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int bgColor = guiScreen.isSelected(category) ?
                new Color(38, 37, 37, 255).getRGB() : Color.BLACK.getRGB();

        context.fill(x, y, x + width, y + height, bgColor);

        context.drawTextWithShadow(mc.textRenderer, category.name, x + (width - mc.textRenderer.getWidth(category.name)) / 2,
                y + (height - mc.textRenderer.fontHeight) / 2,
                guiScreen.isSelected(category) ? -1 : new Color(110, 106, 106, 255).getRGB());

        int outlineColor = new Color(61, 60, 60, 255).getRGB();

        switch (category) {
            case COMBAT -> context.fill(x - 1, y, x, y + height, outlineColor);
            case CLIENT -> context.fill(x + width, y, x + width + 1, y + height, outlineColor);
        }

        context.fill(x, y, x + width, y + 1, outlineColor);
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (isHovered((int) mouseX, (int) mouseY) && button == 0) {
            guiScreen.setCurrentCategory(category);
        }
    }

    private boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }


}
