package yeatware.ui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.ui.settings.Setting;

public class Componenet {
    public final Setting setting;
    public final int x;
    public final int y;
    int length;

    public Componenet(Setting setting, int x, int y) {
        this.setting = setting;
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

    public void keyReleased(int keyCode, int scanCode, int modifiers) {

    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
    }
}