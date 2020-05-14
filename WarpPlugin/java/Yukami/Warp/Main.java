package Yukami.Warp;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        loadConfig();
        new warp(this);
        new setWarp(this);
        new delwarp(this);
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveConfig();
    }
}
