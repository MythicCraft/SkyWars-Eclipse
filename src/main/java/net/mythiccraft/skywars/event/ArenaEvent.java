package net.mythiccraft.skywars.event;

import net.mythiccraft.skywars.game.Arena;
import org.bukkit.event.Event;

/**
 * @author Taylor Hughes
 */
public abstract class ArenaEvent extends Event {

    private final Arena arena;

    public ArenaEvent(Arena arena) {
        this(arena, false);
    }

    public ArenaEvent(Arena arena, boolean async) {
        super(async);
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }
}
