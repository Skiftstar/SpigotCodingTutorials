package Yukiu.langFilesTutorial;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Util {

    private static Map<Player, File> localeSettings = new HashMap<>();
    private static Map<File, Map<String, String>> messages = new HashMap<>();

    public static String getMessage(String locale, String messName) {
        File langFile = new File(Main.getInstance().getDataFolder() + "/locales", locale + ".yml");
        return messages.get(langFile).get(messName);
    }

    public static String getMessage(File file, String messName) {
        return messages.get(file).get(messName);
    }

    public static File getLocale(Player p) {
        return localeSettings.get(p);
    }

    public static void setLocale(Player p, File file) {
        localeSettings.remove(p);
        if (!file.exists()) {
            File locale = new File(Main.getInstance().getDataFolder() + "/locales", "en.yml");
            localeSettings.put(p, locale);
        } else {
            localeSettings.put(p, file);
        }
    }

    public static void removePlayer(Player p) {
        localeSettings.remove(p);
    }

    public static void loadMessages() {
        File langFolder = new File(Main.getInstance().getDataFolder() + "/locales");
        if (!langFolder.exists()) {
            langFolder.mkdir();
        }
        File enFile = new File(langFolder, "en.yml");
        try {
            if (!enFile.exists()) {
                InputStream in = Main.getInstance().getResource("en.yml");
                Files.copy(in, enFile.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File file : langFolder.listFiles()) {
            Map<String, String> localeMessages = new HashMap<>();

            FileConfiguration lang = YamlConfiguration.loadConfiguration(file);
            for (String key : lang.getKeys(false)) {
                for (String messName : lang.getConfigurationSection(key).getKeys(false )) {
                    String message = ChatColor.translateAlternateColorCodes('&', lang.getString(key + "." + messName));
                    localeMessages.put(messName, message);
                }
            }
            messages.put(file, localeMessages);
            System.out.println(file.getName() + "loaded!");
        }
    }
}
