package dev.smelly.customdrops;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomDrops extends JavaPlugin {

  @Override
  public void onEnable() {
    // Plugin startup logic
    this.getServer().getPluginManager().registerEvents(new DropEvent(), this);
    this.getConfig().options().copyDefaults();
    this.saveDefaultConfig();
    ConfigFile.setup();
    ConfigFile.get().options().copyDefaults(true);
    ConfigFile.save();
    this.getCommand("crel").setExecutor(new CrelCommand());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
