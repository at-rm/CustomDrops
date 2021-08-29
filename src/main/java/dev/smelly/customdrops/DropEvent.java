package dev.smelly.customdrops;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

import static org.bukkit.Material.*;

public class DropEvent implements Listener {
  @EventHandler (priority = EventPriority.HIGH)
  public void onBreak(BlockBreakEvent breakEvent) {
    if (breakEvent.isCancelled()) {
      return;
    }
    switch(breakEvent.getBlock().getType()) {
      case GRASS:
      case TALL_GRASS:
        breakEvent.getBlock().setType(AIR);
        CustomDropGenerator grassGenerator = new CustomDropGenerator("grass");
        Optional<ItemStack> grassItem = grassGenerator.generateDrop();
        grassItem.ifPresent(itemStack -> breakEvent.getBlock().getWorld().dropItemNaturally(breakEvent.getBlock().getLocation(), itemStack));
        break;
      case OAK_LEAVES:
      case SPRUCE_LEAVES:
      case BIRCH_LEAVES:
      case JUNGLE_LEAVES:
      case ACACIA_LEAVES:
      case DARK_OAK_LEAVES:
        breakEvent.getBlock().setType(AIR);
        CustomDropGenerator leavesGenerator = new CustomDropGenerator("leaves");
        Optional<ItemStack> leavesItem = leavesGenerator.generateDrop();
        leavesItem.ifPresent(itemStack -> breakEvent.getBlock().getWorld().dropItemNaturally(breakEvent.getBlock().getLocation(), itemStack));
        break;
    }

  }
}
