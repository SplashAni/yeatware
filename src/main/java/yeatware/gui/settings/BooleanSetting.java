package yeatware.gui.settings;

public class BooleanSetting extends Setting {
    boolean value;
    int height;

    public BooleanSetting(String name, boolean value) {
        super(name);
        this.value = value;
        height = 15;
    }

    public int getHeight() {
        return height;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
