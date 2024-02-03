package yeatware.event.events;

import yeatware.event.Event;

public class KeyEvent extends Event {
    int key;
    int action;
    int modifiers;

    public KeyEvent(int key, int action, int modifiers) {
        this.key = key;
        this.action = action;
        this.modifiers = modifiers;
    }

    public int getKey() {
        return key;
    }

    public int getAction() {
        return action;
    }

    public int getModifiers() {
        return modifiers;
    }
}
