package yeatware.system;

import yeatware.system.modules.client.Gui;
import yeatware.system.modules.movement.Fly;
import yeatware.system.modules.movement.Sprint;
import yeatware.system.modules.render.ForceSneak;
import yeatware.system.modules.render.NoLimbInterp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        add(new Fly());
        add(new ForceSneak());
        add(new NoLimbInterp());
        add(new Gui());
        modules.sort(Comparator.comparing(module -> module.getName().toLowerCase()));
    }

    public void add(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getActiveModules() {
        return modules.stream().filter(module -> module.isActive).toList();
    }

    public Module getModule(Class<? extends Module> mod) {
        for (Module module : modules) {
            if (module.getClass() == mod) {
                return module;
            }
        }
        return null;
    }

    public List<Module> getCategoryModules(Category category) {
        return modules.stream().filter(module -> module.getCategory() == category).toList();
    }
}
