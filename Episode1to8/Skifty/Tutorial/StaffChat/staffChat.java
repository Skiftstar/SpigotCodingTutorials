package Skifty.Tutorial.StaffChat;

import Skifty.Tutorial.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class staffChat implements CommandExecutor {

    public staffChat(Main plugin) {
        plugin.getCommand("tc").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only for players");
            return false;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("skifty.staffchat"))) {
            p.sendMessage(Color("&6[Skifty] &cYou don't have enough Permissions"));
            return false;
        }

        if (args.length < 1) {
            p.sendMessage(Color("&6[Skifty] &cmessage cannot be empty"));
            return false;
        }

        String mess = "&b[TC] " + p.getDisplayName() + ": ";
        for (String s : args) {
            mess = mess + s + " ";
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("skifty.staffchat")) {
                player.sendMessage(Color(mess));
            }
        }
        return true;
    }

    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
