package yeatware.ui.containers;

import net.minecraft.client.gui.DrawContext;
import yeatware.system.Module;

import java.awt.*;

import static yeatware.Main.mc;

public class Button {
    GuiScreen guiScreen;
    Module module;
    int x;
    int y;

    public Button(GuiScreen guiScreen, Module module, int x, int y) {
        this.guiScreen = guiScreen;
        this.module = module;
        this.x = x;
        this.y = y;
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {

        if (isHovered((int) mouseX, (int) mouseY)) {
            if (button == 0) {
                if (mc.world == null || mc.player == null) return;
                module.toggle();
            }
            if (button == 1) {
                guiScreen.setModule(module);
            }
        }

    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        context.drawTextWithShadow(mc.textRenderer, module.getName(), x + 5, y + 2,
                module.isActive() ? new Color(45, 79, 147, 255).getRGB() : new Color(110, 106, 106, 255).
                        getRGB()
        );

    }


    private boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + mc.textRenderer.getWidth(module.getName()) && mouseY >= y && mouseY <= y + mc.textRenderer.fontHeight;
    }

}
