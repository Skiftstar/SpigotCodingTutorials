package Yukami.FlySpeed;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class speed implements CommandExecutor {

    public speed() {

    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("flyspeed.speed")) {
            p.sendMessage(Util.Color("&cYou don't have enough permissions!"));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(Util.Color("&cPlease provide a speed from 1 - 10"));
            return false;
        }
        int speed;
        try {
            speed = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            p.sendMessage(Util.Color("&cPease provide a speed from 1 - 10"));
            return false;
        }
        if (speed < 1 || speed > 10) {
            p.sendMessage(Util.Color("&cPease provide a speed from 1 - 10"));
            return false;
        }
        if (p.isFlying()) {
            p.setFlySpeed((float) speed / 10);
        } else {
            p.setWalkSpeed((float) speed/ 10);
        }
        p.sendMessage(Util.Color("&aSpeed set to &b" + speed));
        return true;
    }
}
