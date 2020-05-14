package Skifty.Tutorial;

import Skifty.Tutorial.GetItem.getItem;
import Skifty.Tutorial.HelloCommand.helloCommand;
import Skifty.Tutorial.Listener.listener;
import Skifty.Tutorial.StaffChat.staffChat;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        new helloCommand(this);
        new listener(this);
        new staffChat(this);
        new getItem(this);
        loadConfig();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveConfig();
    }

}
