package yeatware.event;

import meteordevelopment.orbit.ICancellable;

public class Event implements ICancellable {
    @Override
    public void cancel() {
        ICancellable.super.cancel();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancelled) {

    }
}
