package net.mythiccraft.skywars.util;

/**
 * Represents an object that can be unloaded.
 * Used for managers and things that hold data.
 *
 * @author Taylor Hughes
 * @since 1.0.0
 */
public interface Unloadable {

    /**
     * Called when the object is unloaded.
     */
    void unload();
}
