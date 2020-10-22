package Yukiu.langFilesTutorial;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class setLangCMD implements CommandExecutor, Listener, TabCompleter {



    public setLangCMD(Main plugin) {
        plugin.getCommand("setLang").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        FileConfiguration config = Main.getInstance().getConfig();
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (config.get(uuid.toString()) == null) {
            Util.setLocale(p,  "en");
            config.set(uuid.toString(), "en");
            Main.getInstance().saveConfig();
            return;
        }
        String localeFileName = config.getString(uuid.toString());
        Util.setLocale(p, localeFileName.toLowerCase());
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent e) {
        Util.removePlayer(e.getPlayer());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Util.getMessage("en", "PlayerOnly"));
            return false;
        }
        Player p = (Player) commandSender;
        if (strings.length < 1 ) {
            p.sendMessage(Util.getMessage(Util.getLocale(p), "NEArgs"));
            return false;
        }
        String fileName = strings[0];
        Util.setLocale(p, fileName);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> langFiles = new ArrayList<>();
        File folder = new File(Main.getInstance().getDataFolder() + "/locales");
        for (File file : folder.listFiles()) {
            langFiles.add(file.getName().split(".yml")[0]);
        }
        return langFiles;
    }
}
