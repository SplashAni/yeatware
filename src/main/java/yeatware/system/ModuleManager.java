package yeatware.system;

import yeatware.system.modules.client.GuiModule;
import yeatware.system.modules.client.InteractionModule;
import yeatware.system.modules.world.FakePlayer;
import yeatware.system.modules.world.FastUse;
import yeatware.system.modules.combat.Criticals;
import yeatware.system.modules.combat.Feetrap;
import yeatware.system.modules.movement.Fly;
import yeatware.system.modules.movement.LongJump;
import yeatware.system.modules.movement.Sprint;
import yeatware.system.modules.movement.Velocity;
import yeatware.system.modules.player.Scaffold;
import yeatware.system.modules.render.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static yeatware.Main.LOGGER;

public class ModuleManager {
    private static ModuleManager instance;
    private final List<Module> modules;

    private ModuleManager() {
        modules = new ArrayList<>();
    }

    public static ModuleManager get() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    public void init() {
        add(new Sprint());
        add(new Feetrap());
        add(new Fly());
        add(new ForceSneak());
        add(new Criticals());
        add(new LongJump());
        add(new Scaffold());
        add(new DeathEffect());
        add(new InteractionModule());
        add(new NoLimbInterp());
        add(new FakePlayer());
        add(new FullBright());
        add(new GuiModule());
        add(new Chams());
        add(new FakePlayer());
        add(new FastUse());
        add(new Velocity());

        modules.sort(Comparator.comparing(module -> module.getName().toLowerCase()));
    }

    public void add(Module module) {
        LOGGER.info("Added ".concat(module.name).concat(" to yeatware"));
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getActiveModules() {
        return modules.stream().filter(module -> module.isActive).toList();
    }

    public <T extends Module> T getModule(Class<T> mod) {
        for (Module module : modules) {
            if (module.getClass() == mod) {
                return mod.cast(module);
            }
        }
        return null;
    }


    public List<Module> getCategoryModules(Category category) {
        return modules.stream().filter(module -> module.getCategory() == category).toList();
    }
}
