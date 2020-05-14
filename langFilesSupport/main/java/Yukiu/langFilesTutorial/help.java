package Yukiu.langFilesTutorial;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class help implements CommandExecutor {

    public help (Main plugin) {
        plugin.getCommand("example").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Util.getMessage("en", "PlayerOnly"));
            return false;
        }
        Player p = (Player) commandSender;
        p.sendMessage(Util.getMessage(Util.getLocale(p), "helpMessage"));
        return true;
    }
}
