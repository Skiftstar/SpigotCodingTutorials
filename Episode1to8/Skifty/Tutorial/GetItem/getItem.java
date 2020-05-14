package Skifty.Tutorial.GetItem;

import Skifty.Tutorial.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class getItem implements CommandExecutor {

    public getItem(Main plugin) {
        plugin.getCommand("getitem").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only");
            return false;
        }

        Player p = (Player) sender;
        ItemStack is = new ItemStack(Material.DIAMOND, 10);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bDIAMOND"));
        im.addEnchant(Enchantment.DURABILITY, 10, true);
        List<String> test = new ArrayList<>();
        test.add("test");
        test.add("line 2");
        test.add("line 3");
        im.setLore(test);
        is.setItemMeta(im);

        p.getInventory().addItem(is);

        return true;
    }

}
