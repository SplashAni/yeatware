package yeatware;

import io.github.racoondog.norbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yeatware.event.events.KeyEvent;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.system.ModuleManager;
import yeatware.system.modules.client.Gui;
import yeatware.ui.containers.GuiScreen;

import java.lang.invoke.MethodHandles;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("yeat");
    public static final IEventBus EVENT_BUS = EventBus.threadSafe();
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    Category category;

    @Override
    public void onInitialize() {

        ModuleManager.get().init();

        EVENT_BUS.registerLambdaFactory("yeatware", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        EVENT_BUS.subscribe(this);

        LOGGER.info("splash x bennyx x yeat!");
    }

    @EventHandler
    public void onKey(KeyEvent event) {

        if (event.getAction() != GLFW.GLFW_PRESS) return;

        if (event.getKey() == ModuleManager.get().getModule(Gui.class).getKey()
                && !(mc.currentScreen instanceof GuiScreen))
            mc.setScreen(new GuiScreen(category == null ? Category.COMBAT : category, this));


        ModuleManager.get().getModules().stream().takeWhile(mod -> event.getKey() != 0).filter(mod -> mod.getKey() == event.getKey()).forEach(Module::toggle);
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}