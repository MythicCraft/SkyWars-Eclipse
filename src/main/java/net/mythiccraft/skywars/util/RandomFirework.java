package net.mythiccraft.skywars.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;

import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class RandomFirework {

    private static final List<Color> colors = new ArrayList<>();
    private static final List<Type> types = new ArrayList<>();
    private static Random random = null;

    private static void loadColors() {
        colors.add(Color.WHITE);
        colors.add(Color.PURPLE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.AQUA);
        colors.add(Color.BLUE);
        colors.add(Color.FUCHSIA);
        colors.add(Color.GRAY);
        colors.add(Color.LIME);
        colors.add(Color.MAROON);
        colors.add(Color.YELLOW);
        colors.add(Color.SILVER);
        colors.add(Color.TEAL);
        colors.add(Color.ORANGE);
        colors.add(Color.OLIVE);
        colors.add(Color.NAVY);
        colors.add(Color.BLACK);
    }

    private static void loadTypes() {
        types.add(FireworkEffect.Type.BURST);
        types.add(FireworkEffect.Type.BALL);
        types.add(FireworkEffect.Type.BALL_LARGE);
        types.add(FireworkEffect.Type.CREEPER);
        types.add(FireworkEffect.Type.STAR);
    }

    public static void loadFireworks() {
        random = new Random();
        loadColors();
        loadTypes();
    }

    private static FireworkEffect.Type getRandomType() {
        return types.get(random.nextInt(types.size()));
    }

    private static Color getRandomColor() {
        return colors.get(random.nextInt(colors.size()));
    }

    public static void launchRandomFirework(Location location) {
        if (location.getWorld() != null) {
            Firework firework = location.getWorld().spawn(location, Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.setPower(1);
            meta.addEffects(FireworkEffect.builder().flicker(true).with(getRandomType()).withColor(getRandomColor(), getRandomColor()).withFade(getRandomColor()).build());
            firework.setFireworkMeta(meta);
        }
    }
}
