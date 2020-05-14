package Yukiu.langFilesTutorial;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public void onEnable() {
        instance = this;
        loadConfig();
        Util.loadMessages();
        new setLangCMD(this);
        new help(this);
    }

    public static Main getInstance() {
        return instance;
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveConfig();
    }
}
