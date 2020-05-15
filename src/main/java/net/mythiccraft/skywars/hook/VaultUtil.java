package net.mythiccraft.skywars.hook;

import net.milkbowl.vault.economy.Economy;
import net.mythiccraft.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author Taylor Hughes
 */
public class VaultUtil {

    private SkyWars plugin;
    private Economy economy;


    public VaultUtil(SkyWars plugin) {
        this.plugin = plugin;
    }

    public void setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        economy = rsp.getProvider();
    }

    public double getBalance(Player player) {
        if (economy != null) {
            economy.getBalance(player);
        }
        return 0.0D;
    }

    public boolean has(Player player, double amount) {
        if (economy == null) {
            return false;
        }
        return economy.has(player, amount);
    }

    public void takeMoney(Player player, double amount) {
        if (economy == null) {
            return;
        }
        economy.withdrawPlayer(player, amount);
    }

    public void giveMoney(Player player, double amount) {
        if (economy == null) {
            return;
        }
        economy.depositPlayer(player, amount);
    }


}
