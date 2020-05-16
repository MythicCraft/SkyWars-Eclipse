package net.mythiccraft.skywars.event;

import net.mythiccraft.skywars.game.Arena;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Taylor Hughes
 */
public class ArenaStartEvent extends ArenaEvent implements Cancellable {

    private boolean countdown;
    private boolean cancel;
    private static final HandlerList handlers = new HandlerList();

    public ArenaStartEvent(Arena arena) {
        super(arena);
        cancel = false;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
