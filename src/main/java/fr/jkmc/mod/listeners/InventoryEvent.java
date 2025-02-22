package fr.jkmc.mod.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent implements Listener {

    @EventHandler
    public boolean onClick1(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();


        if (event.getCurrentItem() == null) return false;


        if (event.getView().getTitle().equalsIgnoreCase("§8» §fMenu Principal")) {
            event.setCancelled(true);

            /*if (current.getType().equals(Material.DIAMOND_BOOTS) && current.hasItemMeta() && current.getItemMeta().hasDisplayName()) {
                if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§f» §6§lTouche-Touche §8┃ §d§l❤ Populaire")) {
                    if (event.getClick() == ClickType.LEFT) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
                        player.sendMessage("§6§lJKMC §f┃ §7Vous avez rejoint la file de §6ToucheTouche-?.");

                    } else if (event.getClick() == ClickType.RIGHT) {
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                        player.sendMessage("§6§lJKMC §f┃ §cIl y a aucune partie en cours...");
                    }

                }
                return false;


            } else if (current.getType().equals(Material.COMMAND_BLOCK_MINECART) && current.hasItemMeta() && current.getItemMeta().hasDisplayName()) {
                if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§f» §6Host(s)")) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                    HostBar.create(player);
                    player.sendMessage("§6§lJKMC §f┃ §cVous n'avez aucun ticket d'host :(");
                }

            }*/

            return false;
        }

        return false;
    }
}
