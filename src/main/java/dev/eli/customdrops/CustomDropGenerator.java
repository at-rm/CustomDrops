package dev.eli.customdrops;

import com.jojodmo.customitems.api.CustomItemsAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CustomDropGenerator {

  List<DropItem> dropItems;

  public CustomDropGenerator(String block) {
    Map<String, Object> map = ConfigFile.get().getConfigurationSection(block).getValues(true);
    List<Object> items = (ArrayList) map.get("items");

    dropItems = new ArrayList<>();
    for (Object o : items) {
      dropItems.add(DropItem.fromString(o.toString()));
    }

    generateRelativeProbabilitiesList();
  }

  public Optional<ItemStack> generateDrop() {
    Random random = new Random();
    return findItemByProbability(random.nextInt(100));
  }

  private Optional<ItemStack> findItemByProbability(int randomNumber) {
    for (DropItem dropItem : dropItems) {
      if (dropItem.chance > randomNumber) {
        return Optional.of(dropItem.item);
      }
    }
    return Optional.empty();
  }

  private void generateRelativeProbabilitiesList() {
    int previousChance = 0;
    List<DropItem> probabilityItems = new ArrayList<>();

    for (DropItem dropItem : dropItems) {
      probabilityItems.add(new DropItem(dropItem.item, previousChance + dropItem.chance));
    }

    dropItems = probabilityItems;
  }

  private static class DropItem {
    ItemStack item;
    int chance;

    private DropItem(ItemStack item, int chance) {
      this.item = item;
      this.chance = chance;
    }

    static DropItem fromString(String unparsedItem) {
      String[] values = unparsedItem.replace("{", "").replace("}", "").split(", ");
      String name = values[0].replace("name=", "");
      int chance = Integer.parseInt(values[1].replace("chance=", ""));

      if (name.startsWith("custom:")) {
        return new DropItem(CustomItemsAPI.getCustomItem(name.replace("custom:", "")), chance);
      } else {
        return new DropItem(new ItemStack(Material.getMaterial(name.toUpperCase())), chance);
      }
    }
  }

}
