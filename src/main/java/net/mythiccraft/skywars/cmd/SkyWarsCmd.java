package net.mythiccraft.skywars.cmd;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Text;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author Taylor Hughes
 */
public class SkyWarsCmd extends SkyCommand {

    public SkyWarsCmd(SkyWars plugin) {
        super(plugin, "skywars", "skywar", " sw");
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (args.length == 0) {
            if (player.hasPermission("skwywars.admin") || player.hasPermission("skywars.*")) {
                return this.sendHelp(player);
            } else if (player.hasPermission("skywars.help") || player.hasPermission("skywars.user")) {
                return this.sendHelp(player);
            } else {
                return this.noPermission(player);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                if (player.hasPermission("skwywars.admin") || player.hasPermission("skywars.*")) {
                    return this.sendHelp(player);
                } else if (player.hasPermission("skywars.help") || player.hasPermission("skywars.user")) {
                    return this.sendHelp(player);
                } else {
                    return this.noPermission(player);
                }
            }
        }
        return false;
    }

    @Override
    public boolean onConsoleCommand(ConsoleCommandSender console, String[] args) {
        return false;
    }

    public boolean sendHelp(CommandSender sender) {
        sender.sendMessage(Text.COLOR_CHAR + "c" + "Usage: /skywars help!");
        return false;
    }
}
