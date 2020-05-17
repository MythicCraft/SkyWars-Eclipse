package net.mythiccraft.skywars.config;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taylor Hughes
 */
public enum Msg {

    NO_PERMISSION_COMMAND("No-Permission.Command"), NO_PERMISSION_KIT("No-Permission.Kit"),
    NO_PERMISSION_TRAIL("No-Permission.Trail"), NO_PERMISSION_SOULWELL("No-Permission.Soulwell"),
    NO_PERMISSION_VOTE("No-Permission.Vote"),

    PLAYER_OFFLINE("Player-Offline");

    private String path;
    private String msg;
    private String defaultMsg;


    Msg(String path) {
        this(path, "", "");
    }


    Msg(String path, String msg, String defaultMsg) {
        this.path = path;
        this.msg = msg;
        this.defaultMsg = defaultMsg;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    public String getMsg() {
        return msg;
    }

    public String getPath() {
        return path;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Map<Msg, String> cachedMsgs = new HashMap<>();

    public static String getMsg(Msg msg) {
        if (cachedMsgs.get(msg) != null) {
            return Text.colorize(cachedMsgs.get(msg));
        } else {
            return msg.defaultMsg;
        }
    }
}
