package yeatware;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yeatware.event.events.KeyEvent;
import yeatware.system.Category;
import yeatware.system.Module;
import yeatware.system.ModuleManager;
import yeatware.system.modules.client.GuiModule;
import yeatware.ui.containers.GuiScreen;
import yeatware.utils.player.BetaUtils;

import java.lang.invoke.MethodHandles;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("yeat");
    public static final IEventBus BUS = new EventBus();
    @NotNull
    public static MinecraftClient mc = MinecraftClient.getInstance();
    Category category;

    @Override
    public void onInitialize() {

        ModuleManager.get().init();
        BetaUtils.get().init();

        BUS.registerLambdaFactory("yeatware", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        BUS.subscribe(this);

        // mc.gameRenderer.renderHand();
        LOGGER.info("splash x bennyx x yeat!");
    }

    @EventHandler
    public void onKey(KeyEvent event) {

        if (event.getAction() != GLFW.GLFW_PRESS) return;

        if (event.getKey() == ModuleManager.get().getModule(GuiModule.class).getKey()
                && !(mc.currentScreen instanceof GuiScreen))
            mc.setScreen(new GuiScreen(category == null ? Category.COMBAT : category, this));

        ModuleManager.get().getModules().stream().takeWhile(mod -> event.getKey() != 0).filter(mod -> mod.getKey() == event.getKey()).forEach(Module::toggle);
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}