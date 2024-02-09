package yeatware.ui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import yeatware.ui.settings.KeybindSetting;
import yeatware.ui.settings.Setting;

import static yeatware.Main.mc;

public class KeybindComp extends Componenet {
    private final KeybindSetting keybindSetting;
    private boolean isBinding;
    private String currentText;

    public KeybindComp(Setting setting, int x, int y) {
        super(setting, x, y);
        keybindSetting = (KeybindSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        if (isBinding) {
            currentText = "Binding...";
            context.drawText(mc.textRenderer, currentText, x, y, -1, true);
        } else {
            currentText = keybindSetting.getKey() == 0 ? "Key: None" : "Key: " + InputUtil.fromKeyCode(keybindSetting.getKey(), -1).getLocalizedText().getString();
            context.drawText(mc.textRenderer, currentText, x, y, -1, true);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            isBinding = !isBinding;
        }
        super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void keyReleased(int keyCode, int scanCode, int modifiers) {
        if (!isBinding) return;

        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            keybindSetting.setKey(0);
            return;
        }

        keybindSetting.setKey(keyCode);
        isBinding = false;
        super.keyReleased(keyCode, scanCode, modifiers);
    }

    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + mc.textRenderer.getWidth(currentText) && mouseY >= y && mouseY <= y + mc.textRenderer.fontHeight;
    }
}
