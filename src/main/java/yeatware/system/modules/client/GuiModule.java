package yeatware.system.modules.client;

import org.lwjgl.glfw.GLFW;
import yeatware.system.Category;
import yeatware.system.Module;

public class GuiModule extends Module {
    public GuiModule() {
        super("Gui", Category.CLIENT);
        setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

}
