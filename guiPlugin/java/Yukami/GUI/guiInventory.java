package Yukami.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class guiInventory implements Listener {

    private Player p;
    private String name;
    private Inventory inv;
    private ItemStack healIs, giveDiamondIs;

    public guiInventory(Player p, String name, int slots) {
        this.p = p;
        this.name = name;
        inv = Bukkit.createInventory(p, slots, Color(name));
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    public void open() {
        p.openInventory(inv);
    }

    public void setItems() {
        healIs = new ItemStack(Material.POTION);
        ItemMeta im = healIs.getItemMeta();
        im.setDisplayName(Color("&cHeal"));
        healIs.setItemMeta(im);
        inv.setItem(22, healIs);

        giveDiamondIs = new ItemStack(Material.DIAMOND);
        ItemMeta im1 = healIs.getItemMeta();
        im1.setDisplayName(Color("&aFree diamonds!"));
        healIs.setItemMeta(im1);
        inv.setItem(23, giveDiamondIs);
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory clicked = e.getClickedInventory();
        if (clicked == null) {
            return;
        }
        if (!e.getWhoClicked().equals(p)) {
            return;
        }
        if (clicked.equals(p.getInventory())) {
            e.setCancelled(true);
            return;
        }
        if (!clicked.equals(inv)) {
            return;
        }
        e.setCancelled(true);
        if (e.getAction().equals(InventoryAction.HOTBAR_SWAP)) {
            return;
        }
        ItemStack is = e.getCurrentItem();
        if (is == null || is.getType().equals(Material.AIR)) {
            return;
        }

        if (is.equals(healIs)) {
            p.setHealth(20);
            p.setSaturation(20);
        }

        if (is.equals(giveDiamondIs)) {
            p.getInventory().addItem(new ItemStack(Material.DIAMOND));
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        HandlerList.unregisterAll(this);
    }



    private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
