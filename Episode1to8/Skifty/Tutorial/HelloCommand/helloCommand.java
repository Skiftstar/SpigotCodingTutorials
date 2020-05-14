package Skifty.Tutorial.HelloCommand;

import Skifty.Tutorial.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helloCommand implements CommandExecutor {

    public helloCommand(Main plugin) {
        plugin.getCommand("hello").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only");
            return false;
        }

        Player p = (Player) sender;
        p.sendMessage("Hello there!");
        return true;
    }
}
