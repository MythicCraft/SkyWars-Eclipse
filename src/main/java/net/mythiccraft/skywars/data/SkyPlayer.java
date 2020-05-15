package net.mythiccraft.skywars.data;

import net.mythiccraft.skywars.util.Players;
import net.mythiccraft.skywars.util.Text;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author Taylor Hughes
 */
public class SkyPlayer {

    private Player player;
    private UUID uuid;
    private String username, nickname;
    private int kills, deaths, wins, losses, coins, souls;
    private String kits, lastKit;
    private String cages, lastCage;
    private boolean admin;
    private boolean fly;

    public SkyPlayer(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.username = player.getName();
        this.nickname = player.getDisplayName();
        this.kills = 0;
        this.deaths = 0;
        this.wins = 0;
        this.losses = 0;
        this.coins = 0;
        this.souls = 0;
    }

    public int getSouls() {
        return souls;
    }

    public void setLastCage(String lastCage) {
        this.lastCage = lastCage;
    }

    public String getCages() {
        return cages;
    }

    public String getLastCage() {
        return lastCage;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public void giveSouls(int amount) {
        this.souls += amount;
    }

    public void takeSouls(int amount) {
        if (amount >= souls) {
            this.souls -= amount;
        } else {
            this.souls = 0;
        }
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void giveCoins(int amount) {
        this.coins += amount;
    }

    public void takeCoins(int amount) {
        this.coins -= amount;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        player.setDisplayName(nickname);
    }

    public UUID getUUID() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean canFly() {
        return fly;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public String getKits() {
        return kits;
    }

    public void setKits(String kits) {
        this.kits = kits;
    }

    public String getLastKit() {
        return lastKit;
    }

    public void setLastKit(String lastKit) {
        this.lastKit = lastKit;
    }

    public void sendMessage(String... messages) {
        if (Players.isOnline(this.username)) {
            Arrays.stream(messages).forEachOrdered(s -> this.player.sendMessage(Text.colorize(s).replaceAll("%displayname%", player.getDisplayName())));
        }
    }

    public void teleport(Location destination) {
        if (Players.isOnline(this.username)) this.player.teleport(destination);
    }

}
