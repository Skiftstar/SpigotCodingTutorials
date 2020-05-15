package Yukami.GUI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class guiCMD implements CommandExecutor {

    private Main plugin;

    public guiCMD(Main plugin) {

        plugin.getCommand("gui").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        Player p = (Player) commandSender;
        guiInventory gui = new guiInventory(p, "&cTest", 54);
        gui.setItems();
        gui.open();
        return true;
    }
}
