package lkpot.main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Handler implements Listener {

    private final Inspector plugin;

    public Handler(Inspector plugin) {
        this.plugin = plugin;
    }

    Date date = new Date();

    @EventHandler()
    public void onChat (AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String s = plugin.getConfig().getString("supervision.chat");
        if (s == "true") {
            String nickname = e.getPlayer().getName();
            String message = e.getMessage();
            String time = date.toString();
            File logsFile = new File("plugins/Inspector" + File.separator + "logs.yml");
            FileConfiguration logs = YamlConfiguration.loadConfiguration(logsFile);
            List<String> list = logs.getStringList("logs");
            if (list == null) list = new ArrayList<String>();
            list.add(time + " | " + nickname + " typed " + message);
            logs.set("logs", list);
            try {
                logs.save(logsFile);
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String s = plugin.getConfig().getString("supervision.joins");
        if (s == "true") {
            Player p = e.getPlayer();
            String nickname = p.getName();
            InetSocketAddress ip = p.getAddress();
            String time = date.toString();
            File logsFile = new File("plugins/Inspector" + File.separator + "logs.yml");
            FileConfiguration logs = YamlConfiguration.loadConfiguration(logsFile);
            List<String> list = logs.getStringList("logs");
            if (list == null) list = new ArrayList<String>();
            list.add(time + " | " + nickname + " joined | " + ip);
            logs.set("logs", list);
            try {
                logs.save(logsFile);
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        String s = plugin.getConfig().getString("supervision.blocks_operations");
        Player p = e.getPlayer();
        String nickname = p.getName();
        InetSocketAddress ip = p.getAddress();
        Block b = e.getBlock();
        Material material = b.getType();
        String time = date.toString();
        if (s == "true" && material == Material.CHEST || material == Material.ENDER_CHEST || material == Material.TRAPPED_CHEST || material == Material.DROPPER || material == Material.HOPPER
                || material == Material.FURNACE || material == Material.DISPENSER || material == Material.SHULKER_BOX || material == Material.ORANGE_SHULKER_BOX || material == Material.WHITE_SHULKER_BOX
                || material == Material.MAGENTA_SHULKER_BOX || material == Material.LIGHT_BLUE_SHULKER_BOX || material == Material.YELLOW_SHULKER_BOX || material == Material.LIME_SHULKER_BOX
                || material == Material.PINK_SHULKER_BOX || material == Material.GRAY_SHULKER_BOX || material == Material.LIGHT_GRAY_SHULKER_BOX || material == Material.CYAN_SHULKER_BOX
                || material == Material.PURPLE_SHULKER_BOX || material == Material.BLUE_SHULKER_BOX || material == Material.BROWN_SHULKER_BOX || material == Material.GREEN_SHULKER_BOX
                || material == Material.RED_SHULKER_BOX || material == Material.BLACK_SHULKER_BOX || material == Material.BARREL) {
            File logsFile = new File("plugins/Inspector" + File.separator + "logs.yml");
            FileConfiguration logs = YamlConfiguration.loadConfiguration(logsFile);
            List<String> list = logs.getStringList("logs");
            if (list == null) list = new ArrayList<String>();
            list.add(time + " | " + nickname + " placed " + material + " | " + ip);
            logs.set("logs", list);
            try {
                logs.save(logsFile);
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onDestroy (BlockBreakEvent e) {
        String s = plugin.getConfig().getString("supervision.blocks_operations");
        Player p = e.getPlayer();
        String nickname = p.getName();
        InetSocketAddress ip = p.getAddress();
        Block b = e.getBlock();
        Material material = b.getType();
        String time = date.toString();
        if (s == "true" && material == Material.CHEST || material == Material.ENDER_CHEST || material == Material.TRAPPED_CHEST || material == Material.DROPPER || material == Material.HOPPER
        || material == Material.FURNACE || material == Material.DISPENSER || material == Material.ORANGE_SHULKER_BOX || material == Material.WHITE_SHULKER_BOX
                || material == Material.MAGENTA_SHULKER_BOX || material == Material.LIGHT_BLUE_SHULKER_BOX || material == Material.YELLOW_SHULKER_BOX || material == Material.LIME_SHULKER_BOX
                || material == Material.PINK_SHULKER_BOX || material == Material.GRAY_SHULKER_BOX || material == Material.LIGHT_GRAY_SHULKER_BOX || material == Material.CYAN_SHULKER_BOX
                || material == Material.PURPLE_SHULKER_BOX || material == Material.BLUE_SHULKER_BOX || material == Material.BROWN_SHULKER_BOX || material == Material.GREEN_SHULKER_BOX
                || material == Material.RED_SHULKER_BOX || material == Material.BLACK_SHULKER_BOX || material == Material.BARREL || material == Material.SHULKER_BOX) {
            File logsFile = new File("plugins/Inspector" + File.separator + "logs.yml");
            FileConfiguration logs = YamlConfiguration.loadConfiguration(logsFile);
            List<String> list = logs.getStringList("logs");
            if (list == null) list = new ArrayList<String>();
            list.add(time + " | " + nickname + " brake " + material + " | " + ip);
            logs.set("logs", list);
            try {
                logs.save(logsFile);
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }
}
