package dev.smelly.customdrops;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CrelCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    System.out.println("Reloaded config for FancyDrop");
    ConfigFile.reload();
    return true;
  }
}
