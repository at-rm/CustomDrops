package dev.eli.customdrops;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
  private static File file;
  private static FileConfiguration customFile;

  public static void setup() {
    file = new File(Bukkit.getServer().getPluginManager().getPlugin("CustomDrops").getDataFolder(), "config.yml");
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException var1) {
        System.out.println("Couldn't create config file for CustomDrops.");
      }
    }

    customFile = YamlConfiguration.loadConfiguration(file);
  }

  public static FileConfiguration get() {
    return customFile;
  }

  public static void save() {
    try {
      customFile.save(file);
    } catch (IOException var1) {
      System.out.println("Couldn't save config file for CustomDrops.");
    }

  }

  public static void reload() {
    customFile = YamlConfiguration.loadConfiguration(file);
  }
}
