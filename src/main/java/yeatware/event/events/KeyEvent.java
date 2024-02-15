package yeatware.event.events;

import yeatware.event.Event;

public class KeyEvent extends Event {
    int key;
    int action;
    int modifiers;

    int scanCode;

    public KeyEvent(int key, int action, int modifiers, int scanCode) {
        this.key = key;
        this.action = action;
        this.modifiers = modifiers;
        this.scanCode = scanCode;
    }

    public int getKey() {
        return key;
    }

    public int getScanCode() {
        return scanCode;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }
}
