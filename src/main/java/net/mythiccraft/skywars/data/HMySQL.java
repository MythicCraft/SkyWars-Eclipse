package net.mythiccraft.skywars.data;

import org.bukkit.entity.Player;

import java.sql.Connection;

/**
 * @author Taylor Hughes
 */
public class HMySQL implements Storage {

    /**
     * Get the type/method used to store information.
     *
     * @return The storage type
     */
    @Override
    public StorageType getType() {
        return null;
    }

    /**
     * Connects to the database.
     */
    @Override
    public void connect() {

    }

    /**
     * Does the storage still have connection?
     *
     * @return True if connection is there
     */
    @Override
    public boolean hasConnection() {
        return false;
    }

    /**
     * Shuts down the connection.
     */
    @Override
    public void shutdown() {

    }

    /**
     * Closes the connection and then restarts.
     */
    @Override
    public void reconnect() {

    }

    /**
     * Get the connection associated with this storage.
     *
     * @return The connection
     */
    @Override
    public Connection getConnection() {
        return null;
    }

    /**
     * Save a player's data
     *
     * @param player The player
     */
    @Override
    public void savePlayer(Player player) {

    }
}
