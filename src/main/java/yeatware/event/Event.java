package yeatware.event;

import meteordevelopment.orbit.ICancellable;

public class Event implements ICancellable {
    private boolean cancelled = false;
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public void cancel() {
        this.setCancelled(true);
    }
}
