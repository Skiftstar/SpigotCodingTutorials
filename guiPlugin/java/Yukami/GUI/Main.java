package Yukami.GUI;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public void onEnable() {
        instance = this;
        new guiCMD(this);
    }

    public static Main getInstance() {
        return instance;
    }
}
