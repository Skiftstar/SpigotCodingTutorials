package Skifty.Tutorial.Listener;

import Skifty.Tutorial.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class listener implements Listener {

    private Plugin plugin = Main.getPlugin(Main.class);
    private int count = 0;

    public listener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&a" + p.getDisplayName() + " &bhas joined"));
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent e1) {
        Player p = e1.getPlayer();
        e1.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&a" + p.getDisplayName() + " &bhas left"));
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent e2) {
        Action action = e2.getAction();
        Player p = e2.getPlayer();
        Block b = e2.getClickedBlock();

        if (!(action.equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }
        if (!(b.getType().equals(Material.DIAMOND_BLOCK))) {
            return;
        }

        p.setHealth(20);
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent e3) {
        Block b = e3.getBlockPlaced();
        Player p =  e3.getPlayer();

        if (!(p.hasPermission("skifty.place"))) {
            e3.setCancelled(true);
            return;
        }
            plugin.getConfig().set("Blocks.TnT." + count + ".world", b.getWorld().getName());
            plugin.getConfig().set("Blocks.TnT." + count + ".Type", b.getType().toString());
            plugin.getConfig().set("Blocks.TnT." + count + ".X", b.getX());
            plugin.getConfig().set("Blocks.TnT." + count + ".Y", b.getY());
            plugin.getConfig().set("Blocks.TnT." + count + ".Z", b.getZ());
            plugin.saveConfig();
            count++;
    }

}
