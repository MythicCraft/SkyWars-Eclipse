package net.mythiccraft.skywars.data;

/**
 * @author Taylor Hughes
 */
public enum StorageType {

    MYSQL("MySQL"), SQLITE("SQLite");

    private final String display;

    StorageType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
