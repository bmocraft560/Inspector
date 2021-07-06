package lkpot.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Inspector extends JavaPlugin {
    public void onEnable () {

        File config = new File(getDataFolder() + File.separator + "config.yml");
        if(!config.exists()) {
            getLogger().info("Creating new config file...");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        File logs = new File(getDataFolder() + File.separator + "logs.yml");
        if(!logs.exists()) {
            getLogger().info("Creating new logs file...");
            try {
                logs.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bukkit.getPluginManager().registerEvents((Listener) new Handler(this),this);
        getLogger().info("Plugin was enabled.");
    }

    public void onDisable () {
        getLogger().info("Plugin was disabled.");
    }
}
