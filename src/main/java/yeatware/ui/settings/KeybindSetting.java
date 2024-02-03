package yeatware.ui.settings;

import yeatware.system.Module;

public class KeybindSetting extends Setting {
    int key;
    Module module;

    public KeybindSetting(String name, Module module, int key) {
        super(name);
        this.key = key;
        this.module = module;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
        module.setKey(key);
    }
}
