package net.mythiccraft.skywars.data;

import net.mythiccraft.skywars.SkyWars;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Taylor Hughes
 */
public class MySQL implements Storage {

    private SkyWars plugin;
    private String host, port;
    private String database;
    private String username, password;
    private Connection connection;
    private String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS 'skywars_data' " +
            "('uuid' VARCHAR(40) primary key unique, " +
            "'username' VARCHAR(24), 'kills' INT(12) DEFAULT 0, " +
            "'deaths' INT(12) DEFAULT 0, 'coins' INT(12) DEFAULT 0, " +
            "'souls' INT(12) DEFAULT 0, 'wins' INT(12) DEFAULT 0, " +
            "'losses' INT(12) DEFAULT 0, 'blocks_broken' INT(12) DEFAULT 0, " +
            "'arrows_fired' INT(12) DEFAULT 0, " +
            "'time_played' VARCHAR(16), 'cages' VARCHAR(32), " +
            "'last_cage' VARCHAR(16), 'last_trail' VARCHAR(16));";

    public MySQL(SkyWars plugin) {
        this.plugin = plugin;
        this.host = plugin.getMainConfig().getString("MySQL.Host", "localhost");
        this.port = String.valueOf(plugin.getMainConfig().getInt("MySQL.Port", 3306));
        this.database = plugin.getMainConfig().getString("MySQL.Database");
        this.username = plugin.getMainConfig().getString("MySQL.Username", "root");
        this.password = plugin.getMainConfig().getString("MySQL.Password", "root");
    }


    /**
     * Get the type/method used to store information.
     *
     * @return The storage type
     */
    @Override
    public StorageType getType() {
        return StorageType.MYSQL;
    }

    /**
     * Connects to the database.
     */
    @Override
    public void connect() {
        if (!this.hasConnection()) {
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            return this.connection != null && !this.connection.isClosed();
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
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.getConnection().close();
                SkyWars.sql("The MySQL connection has been shutdown successfully!");
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
        SkyWars.sql("Attempting to restart the MySQL database connection...");
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
        return this.connection;
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
