package yeatware.ui.settings;

import org.apache.commons.lang3.ArrayUtils;

public class ModeSetting<T extends Enum<T>> extends Setting {

    private final T[] modes;
    private T mode;

    public ModeSetting(String name, T mode) {
        super(name);
        this.modes = mode.getDeclaringClass().getEnumConstants();
        this.mode = mode;
    }

    public T getMode() {
        return mode;
    }

    public void setMode(T mode) {
        this.mode = mode;
    }

    public void nextMode() {
        int currentIndex = ArrayUtils.indexOf(modes, mode);
        mode = modes[(currentIndex + 1) % modes.length];
    }
}
