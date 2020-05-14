package Yukami.FlySpeed;

import org.bukkit.*;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JavaPlugin {

    public void onEnable() {
        getCommand("fly").setExecutor(new fly());
        getCommand("speed").setExecutor(new speed());
    }

    @SuppressWarnings({ "deprecation" })
    public static ItemStack selector(String itemStack) {
        ItemStack is = new ItemStack(Material.AIR);
        String[] strings = itemStack.split(" ");
        String[] item = strings[0].split(":");
        if(item.length > 1) {
            Material m = Material.getMaterial(item[0]);
            is.setType(m);
            is.setDurability(Short.parseShort(item[1]));
        }
        else if(isInt(item[0])){
            Material m = Material.getMaterial(item[0]);
            is.setType(m);
        }
        else{
            Material m = Material.getMaterial(item[0]);
            is.setType(m);
        }

        int amount = 1;
        if (isInt(strings[1])) {
            amount = Integer.parseInt(strings[1]);
        }
        is.setAmount(amount);
        for (int i = 2; i < strings.length; i++){
            String s = strings[i];
            String[] trim = s.split(";");
            if (trim.length >= 1) {
                if (trim[0].equalsIgnoreCase("name")){
                    ItemMeta im = is.getItemMeta();
                    String name = color(replace(trim[1], "_", " "));
                    im.setDisplayName(name);
                    is.setItemMeta(im);
                }
                else if (trim[0].equalsIgnoreCase("opis")){
                    ItemMeta im = is.getItemMeta();
                    trim[1] =  color(replace(trim[1], "_", " "));
                    String[] lorestring = trim[1].split("##");
                    List<String> lore = new ArrayList<>();
                    for (String s1 : lorestring) {
                        lore.add(color(s1));
                    }
                    im.setLore(lore);
                    is.setItemMeta(im);
                }
            }
        }
        return is;
    }

    public static String color(String s) {
        if(s == null) return "";
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {}
        return false;
    }

    public static List<String> fromString(String s){
        List<String> list = new ArrayList<>();
        if(s == null || s.isEmpty()) return list;
        list = Arrays.asList(s.split(","));
        return list;
    }

    public static String toString(List<String> list, boolean send){
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
            sb.append(',');
            if(send) sb.append(' ');
        }
        String s = sb.toString();
        if(send) if(s.length() > 2) s = s.substring(0, s.length()-2);
        else if(s.length() > 1) s = s.substring(0, s.length()-1);
        return s;
    }

    public static Location parseLocation(String string){
        if(string == null) return null;
        String[] shs = string.split(",");
        if(shs.length < 4) return null;
        World world = Bukkit.getWorld(shs[0]);
        if(world == null) world = Bukkit.getWorlds().get(0);
        Location loc = new Location(world, Integer.valueOf(shs[1]), Integer.valueOf(shs[2]), Integer.valueOf(shs[3]));
        return loc;
    }

    public static String toString(Location loc){
        if(loc == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(loc.getWorld().getName());
        sb.append(",");
        sb.append(loc.getBlockX());
        sb.append(",");
        sb.append(loc.getBlockY());
        sb.append(",");
        sb.append(loc.getBlockZ());
        return sb.toString();
    }

    public static int check(String s){
        int i = 0;
        try{
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {}
        return i;
    }

    public static String replace(String text, String searchString, String replacement){
        if ((text == null) || (text.isEmpty()) || (searchString.isEmpty()) || (replacement == null)) {
            return text;
        }
        int start = 0;
        int max = -1;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        int replacedLength = searchString.length();
        int increase = replacement.length() - replacedLength;
        increase = increase < 0 ? 0 : increase;
        increase *= (max > 64 ? 64 : max < 0 ? 16 : max);
        StringBuilder sb = new StringBuilder(text.length() + increase);
        while (end != -1)
        {
            sb.append(text.substring(start, end)).append(replacement);
            start = end + replacedLength;
            max--;
            if (max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        sb.append(text.substring(start));
        return sb.toString();
    }

}
