package net.mythiccraft.skywars.config;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.SkyWarsAPI;
import net.mythiccraft.skywars.menu.GUI;
import net.mythiccraft.skywars.menu.Item;
import net.mythiccraft.skywars.util.ItemBuilder;
import net.mythiccraft.skywars.util.Text;
import net.mythiccraft.skywars.util.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Taylor Hughes
 */
public class GUIConfig extends FileConfig {

    public GUIConfig(SkyWars plugin, File file) {
        super(plugin, file);
    }

    public String getGUIName() {
        return getString("Name");
    }

    public int getSize() {
        return getInt("Size", 9);
    }

    public Item getItem(String path) {
        ItemBuilder builder = new ItemBuilder(Material.getMaterial(getString("Items." + path + ".Material")), getInt("Items." + path + ".Amount"));
        List<String> actions = getStringList("Items." + path + ".On-Click");
        return new Item(builder.build(), getInt("Items." + path + ".Slot")) {
            @Override
            public void onClick(InventoryClickEvent e) {
                if (actions != null && !actions.isEmpty()) {
                    for (String s : actions) {
                        String[] str2;
                        if (s.startsWith("[Message] ")) {
                            String s1 = s.replaceAll("[Message] ", "")
                                    .replaceAll("%playername%", e.getWhoClicked().getName())
                                    .replaceAll("%displayname%", ((Player) e.getWhoClicked()).getDisplayName());
                            e.getWhoClicked().sendMessage(s1);
                        }

                        else if (s.startsWith("[Broadcast] ")) {
                            Bukkit.broadcastMessage(s.replaceAll("[Broadcast] ", "")
                                    .replaceAll("%playername%", e.getWhoClicked().getName())
                                    .replaceAll("%displayname%", ((Player) e.getWhoClicked()).getDisplayName()));
                        }

                        else if (s.startsWith("[GiveItem] ")) {
                          String s1 = s.replaceAll("[GiveItem] ", "");
                          String[] str = s1.split(" ");
                          int amount = 1;
                          String material = "STONE";
                          if (str.length >= 1) {
                               material = str[0];
                          }

                          if (str.length >= 2) {
                              amount = Integer.parseInt(str[1]);
                          }

                          ItemBuilder builder1 = new ItemBuilder(Material.matchMaterial(material), amount);
                          if (str.length >= 3) {
                              if (str[2].contains("name:")) {
                                  String[] str1 = str[2].split(":");
                                  builder1.setName(str1[1].replaceAll("_", " "));
                              }

                              else if (str[2].contains("lore:")) {
                                  List<String> lore = new ArrayList<>();
                                  String lore1 = str[2].split(":")[1];
                                  if (lore1.contains("\n")) {
                                      str2 = lore1.split("\n");
                                      lore = Arrays.stream(str2).map(s4 -> Text.colorize(s4.replaceAll("_", " "))).collect(Collectors.toList());
                                  } else {
                                      lore.add(Text.colorize(lore1.replaceAll("_", " ")));
                                  }
                                  builder1.setLore(lore);
                              }
                          }

                          if (str.length >= 4) {}

                          e.getWhoClicked().getInventory().setItem(e.getWhoClicked().getInventory().firstEmpty(), builder1.build());
                        }

                        else if (s.startsWith("[VaultGive] ")) {
                            str2 = s.split(" ");
                            getPlugin().getVaultUtil().giveMoney((Player) e.getWhoClicked(), Double.parseDouble(str2[1]));
                        }

                        else if (s.startsWith("[VaultTake] ")) {
                            str2 = s.split(" ");
                            getPlugin().getVaultUtil().takeMoney((Player) e.getWhoClicked(), Double.parseDouble(str2[1]));
                        }

                        else if (s.startsWith("[Title] ")) {
                            String finalTitle = s.replaceAll("[Title] ", "");
                            String subtitle = "";
                            if (finalTitle.contains("\n")) {
                                String[] titleSplit = finalTitle.split("\n");
                                finalTitle = titleSplit[0];
                                subtitle = titleSplit[1];
                            }
                            Title title = new Title(finalTitle.replaceAll("%displayname%", ((Player) e.getWhoClicked()).getDisplayName()).replaceAll("%playername%", e.getWhoClicked().getName()), subtitle.replaceAll("%displayname%", ((Player) e.getWhoClicked()).getDisplayName()).replaceAll("%playername%", e.getWhoClicked().getName()));
                            title.send((Player) e.getWhoClicked());
                        }

                        else if (s.startsWith("[GiveSouls] ")) {
                            str2 = s.split(" ");
                            SkyWarsAPI.giveSouls((Player) e.getWhoClicked(), Integer.parseInt(str2[1]));
                        }

                        else if (s.startsWith("[TakeSouls] ")) {
                            str2 = s.split(" ");
                            SkyWarsAPI.takeSouls((Player) e.getWhoClicked(), Integer.parseInt(str2[1]));
                        }

                        else if (s.startsWith("[GiveCoins] ")) {
                            str2 = s.split(" ");
                            SkyWarsAPI.giveCoins((Player) e.getWhoClicked(), Integer.parseInt(str2[1]));
                        }

                        else if (s.startsWith("[TakeCoins] ")) {
                            str2 = s.split(" ");
                            SkyWarsAPI.takeCoins((Player) e.getWhoClicked(), Integer.parseInt(str2[1]));
                        }
                    }
                }
            }
        };
    }

    public Map<Integer, Item> getItems() {
        return getConfigSection("Items").getKeys(false).stream().map(this::getItem).collect(Collectors.toMap(Item::getSlot, item -> item, (a, b) -> b));
    }

    public GUI build() {
        GUI gui = new GUI(this.getPlugin(), this.getGUIName());
        gui.setItems(getItems());
        return gui;
    }
}
