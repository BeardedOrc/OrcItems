package me.beardedorc.orcitems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private OrcItems plugin = OrcItems.getInstance();
    private Map<String, ItemStack> customItemData = new HashMap<>();
    private ConfigManager configManager;

    private Map<String, ItemStack> loadItemData() {
        customItemData.clear();
        for (String name :  configManager.getCustomItemsCFG().getConfigurationSection("Items").getKeys(false)){
            customItemData.put(name, configManager.getCustomItemsCFG().getItemStack(name));
        }
        return customItemData;
    }

    private boolean checkItemExistsFile(String name) {
        if (!(configManager.getCustomItemsCFG().getConfigurationSection("Items").contains(name))) {
            return false;
        }
        return true;
    }
    private boolean checkItemExistsMap(String name) {
        if (!(customItemData.containsKey(name))) {
            return false;
        }
        return true;
    }

    private ItemStack getItem(String name) {
        if (!(checkItemExistsMap(name))) {
            ItemStack item = new ItemStack(Material.AIR, 1);
            return item;
        } else {
            ItemStack item = customItemData.get(name);
            return item;
        }
    }

    private void saveItemDataToFile(String name, ItemStack item) {
        if (!(checkItemExistsFile(name))) {
            configManager.getCustomItemsCFG().set(name, item);
            configManager.saveCustomItems();
        } else {
            // duplicate item
        }
    }

    private void removeItemDataFromFile(String name, ItemStack item) {
        if (!(checkItemExistsFile(name))) {
            configManager.getCustomItemsCFG().set("Items." + name, null);
            configManager.saveCustomItems();
        } else {
            // duplicate item
        }
    }

    private void saveItemDataToMap(String name, ItemStack item) {
        if (!(checkItemExistsMap(name))) {
            customItemData.put(name, item);
        } else {
            // duplicate item
        }
    }

    private void removeItemDataFromMap(String name, ItemStack item) {
        if (!(checkItemExistsMap(name))) {
            customItemData.remove(name);
        } else {
            // duplicate item
        }
    }

    private void giveItem(Player player, ItemStack item){
        player.getInventory().addItem(item);
    }

    private void dropItem(Location location, ItemStack item){

    }

    private ItemStack itemStackBuilder(String name, int quantity) {
        ItemStack item = getItem(name);
        item.setAmount(quantity);
        return item;
    }



}
