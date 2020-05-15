package net.mythiccraft.skywars.cmd;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Text;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

/**
 * A custom command
 *
 * @author Taylor Hughes
 */
public abstract class SkyCommand implements CommandExecutor {

    protected SkyWars plugin;
    private String command;
    private String[] aliases;

    protected SkyCommand(SkyWars plugin, String command, String... aliases) {
        this.plugin = plugin;
        this.command = command;
        this.aliases = aliases;
    }

    @Override
    public String toString() {
        return this.command;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public String getCommand() {
        return this.command;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase(this.command)) {
            if (sender instanceof Player) {
                return this.onPlayerCommand((Player) sender, args);
            } else {
                return this.onConsoleCommand((ConsoleCommandSender) sender, args);
            }
        } else {
            return false;
        }
    }

    public abstract boolean onPlayerCommand(Player player, String[] args);

    public abstract boolean onConsoleCommand(ConsoleCommandSender console, String[] args);

    public boolean noPermission(Player player) {
        player.sendMessage(Text.colorize("&cYou do not have permission to use this command!"));
        return false;
    }
}
