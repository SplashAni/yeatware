package yeatware.system;

import yeatware.system.modules.Fly;
import yeatware.system.modules.Sprint;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static ModuleManager instance;
    private List<Module> modules;

    private ModuleManager() {
        modules = new ArrayList<>();
    }

    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    public void init() {
        modules.add(new Fly());
        modules.add(new Sprint());
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getCategoryModules(Category category) {
        return modules.stream().filter(module -> module.getCategory() == category).toList();
    }
}
