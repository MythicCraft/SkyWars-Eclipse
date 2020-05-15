package net.mythiccraft.skywars.event;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.menu.GUI;
import net.mythiccraft.skywars.menu.GUIHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taylor Hughes
 */
public class InventoryListener implements Listener {

    private SkyWars plugin;

    public InventoryListener(SkyWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
            if (e.getClickedInventory().getHolder() instanceof GUIHolder) {
                e.setCancelled(true);
                GUI gui = ((GUIHolder) e.getClickedInventory().getHolder()).getGUI();
                gui.onClick(e);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof GUIHolder) {
            ((GUIHolder) e.getInventory().getHolder()).getGUI().close((Player) e.getPlayer());
        }
    }
}
