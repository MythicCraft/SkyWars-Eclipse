package net.mythiccraft.skywars.game;

/**
 * @author Taylor Hughes
 */
public enum ArenaState {

    WAITING(true), IN_PROGRESS(false), ENDING(false), ROLLING_BACK(false), DISABLED(false);

    private final boolean joinable;

    ArenaState(boolean joinable) {
        this.joinable = joinable;
    }

    public boolean isJoinable() {
        return joinable;
    }
}
