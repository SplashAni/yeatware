package yeatware.ui.settings;

public class NumberSetting extends Setting {
    int max;
    int min;
    int value;
    int increment;

    public NumberSetting(String name, int value, int min, int max, int increment) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public NumberSetting(String name, int value, int min, int max) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = 1;
    }

    public void increment() {
        value += (increment >= 0) ? increment : -increment;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getIncrement() {
        return increment;
    }

    public double getDouble() {
        return value;
    }

    public float getFloat() {
        return value;
    }
}

