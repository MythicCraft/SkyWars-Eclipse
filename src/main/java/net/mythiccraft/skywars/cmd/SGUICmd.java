package net.mythiccraft.skywars.cmd;

import net.mythiccraft.skywars.SkyWars;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author Taylor Hughes
 */
public class SGUICmd extends SkyCommand {

    public SGUICmd(SkyWars plugin) {
        super(plugin, "sgui", "skygui");
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        if (args.length == 0) {
            return false;
        } else if (args.length == 1) {
            plugin.getGUIManager().openGUI(player, args[0]);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("open")) {
                if (args[1].equalsIgnoreCase("testgui")) {
                    plugin.getTestGUI().open(player);
                }
            }
        }
        return false;
    }

    @Override
    public boolean onConsoleCommand(ConsoleCommandSender console, String[] args) {
        return false;
    }
}
