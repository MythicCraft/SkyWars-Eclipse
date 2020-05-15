package net.mythiccraft.skywars.util;

import net.mythiccraft.skywars.SkyWars;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * @author Taylor Hughes
 */
public class BungeeUtil {

    public static void tpToServer(Player player, String s1, String serverName) {
        if (!s1.equalsIgnoreCase("")) {
            player.sendMessage(s1);
        }
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(byteStream);
            dataStream.writeUTF("Connect");
            dataStream.writeUTF(serverName);
            player.sendPluginMessage(SkyWars.getInstance(), "BungeeCord", byteStream.toByteArray());
            byteStream.close();
            dataStream.close();
        } catch (Exception e) {
            player.sendMessage(Text.colorize("&c&lERROR! &r&cCould not send you to &e" + serverName));
        }
    }
}
