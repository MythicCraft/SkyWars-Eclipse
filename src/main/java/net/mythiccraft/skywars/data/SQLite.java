package net.mythiccraft.skywars.data;

import net.mythiccraft.skywars.SkyWars;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Taylor Hughes
 */
public class SQLite implements Storage {

    private SkyWars plugin;
    private File databaseFile;
    private Connection connection;
    private String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS 'skywars_data' " +
            "('uuid' VARCHAR(40) primary key unique, " +
            "'username' VARCHAR(24), 'kills' INT(12) DEFAULT 0, " +
            "'deaths' INT(12) DEFAULT 0, 'coins' INT(12) DEFAULT 0, " +
            "'souls' INT(12) DEFAULT 0, 'wins' INT(12) DEFAULT 0, " +
            "'losses' INT(12) DEFAULT 0, 'blocks_broken' INT(12) DEFAULT 0, " +
            "'arrows_fired' INT(12) DEFAULT 0, " +
            "'time_played' VARCHAR(16), 'cages' VARCHAR(32), " +
            "'last_cage' (VARCHAR(16)));";

    public SQLite(SkyWars plugin) {
        this.plugin = plugin;
        this.databaseFile = new File(plugin.getDataFolder(), "skywars.db");
        try {
            if (!databaseFile.exists() && !databaseFile.createNewFile()) {
                SkyWars.sql("Failed to create database file! Most things will be disabled.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the type/method used to store information.
     *
     * @return The storage type
     */
    @Override
    public StorageType getType() {
        return StorageType.SQLITE;
    }

    /**
     * Connects to the database.
     */
    @Override
    public void connect() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Does the storage still have connection?
     *
     * @return True if connection is there
     */
    @Override
    public boolean hasConnection() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Shuts down the connection.
     */
    @Override
    public void shutdown() {
        SkyWars.sql("Attempting to close SQLite connection.");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                SkyWars.sql("SQLite connection shutdown successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection and then restarts.
     */
    @Override
    public void reconnect() {
        SkyWars.sql("Attempting to reconnect to the database.");
        shutdown();
        connect();
    }

    /**
     * Get the connection associated with this storage.
     *
     * @return The connection
     */
    @Override
    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
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
