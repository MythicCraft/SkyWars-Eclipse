package net.mythiccraft.skywars.data;

import org.bukkit.entity.Player;

import java.sql.Connection;

/**
 * A storage interface.
 *
 * @author Taylor Hughes
 */
public interface Storage {

    /**
     * Get the type/method used to store information.
     *
     * @return The storage type
     */
    StorageType getType();

    /**
     * Get the display name of the storage type.
     *
     * @return The display name
     */
    default String getName() {
        return getType().getDisplay();
    }

    /**
     * Connects to the database.
     */
    void connect();

    /**
     * Does the storage still have connection?
     *
     * @return True if connection is there
     */
    boolean hasConnection();

    /**
     * Shuts down the connection.
     */
    void shutdown();

    /**
     * Closes the connection and then restarts.
     */
    void reconnect();

    /**
     * Get the connection associated with this storage.
     *
     * @return The connection
     */
    Connection getConnection();

    /**
     * Save a player's data
     *
     * @param player The player
     */
    void savePlayer(Player player);

}
