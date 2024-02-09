package yeatware.ui.containers.comp;

import net.minecraft.client.gui.DrawContext;
import yeatware.ui.settings.ModeSetting;
import yeatware.ui.settings.Setting;

import static yeatware.Main.mc;

public class ModeComp extends Componenet {
    ModeSetting<?> modeSetting;
    int maxLength;

    public ModeComp(Setting setting, int x, int y, int maxLength) {
        super(setting, x, y);
        this.modeSetting = (ModeSetting<?>) setting;
        this.maxLength = maxLength;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {


        context.drawText(mc.textRenderer, setting.getName(), x, y, -1, true);

        context.drawText(mc.textRenderer, modeSetting.get().name(), x + mc.textRenderer.getWidth(setting.getName()) + 5, y, -1, true);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) modeSetting.nextMode();
        super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + mc.textRenderer.getWidth(setting.getName()) + 5 + mc.textRenderer.getWidth(modeSetting.get().name()) && mouseY >= y && mouseY <= y + mc.textRenderer.fontHeight;
    }

}
