package fr.jkmc.mod.utils.guis.ban;

import fr.jkmc.mod.utils.CustomItemStack;
import fr.jkmc.mod.utils.guimanager.PaginatedMenu;

import fr.jkmc.mod.utils.guimanager.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class BanMenu extends PaginatedMenu {

    public BanMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.WHITE + playerMenuUtility.getPlayerToSS().getName() + " §8» §cBan";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        ArrayList<Player> players = new ArrayList<Player>(getServer().getOnlinePlayers());


        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cFermer l'inventaire")) {
            p.closeInventory();

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §fCheat")) {
                p.sendMessage("coucou");
                playerMenuUtility.getPlayerToSS().kickPlayer("§6§lJKMC §f┃ §7Vous avez été Éxpulsé ! \n \n§8▪ §7Pour: §bCheat (X-Ray) \n§8▪ §7Durée: §b0 secondes \n \n§f§lEst-ce un Problème ? \n§f§oContactez nous sur discord.jkmc.fr !");

            } else if (e.getCurrentItem().getType().equals(Material.ARROW)) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aPage Précédente")) {
                    if (page == 0) {
                        p.sendMessage("§c§lJKSTAFF §f┃ §cVous êtes déjà sur la première page.");
                    } else {
                        page = page - 1;
                        super.open();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aPage Suivante")) {
                    if (!((index + 1) >= players.size())) {
                        page = page + 1;
                        super.open();
                    } else {
                        p.sendMessage("§c§lJKSTAFF §f┃ §cVous êtes sur la dernière page.");
                    }
                }
            }

    }

    @Override
    public void setMenuItems() {

        addMenuBorder();

        inventory.setItem(21, new CustomItemStack(Material.PAPER).setName("§8» §fCheat").setLore("\n §8▪ §7Type: §bX-Ray \n §8▪ §7Durée: §b3 Jour(s) \n \n§6§l» §eClique: §fAppliquer la Sanction."));


        ItemStack playerHead1 = new ItemStack(Material.LEGACY_SKULL_ITEM);
        SkullMeta skullMeta = (SkullMeta) playerHead1.getItemMeta();
        skullMeta.setOwner(playerMenuUtility.getPlayerToSS().getName());
        playerHead1.setItemMeta(skullMeta);

        inventory.setItem(4, new CustomItemStack(playerHead1).setName("§8» §f" + playerMenuUtility.getPlayerToSS().getName()).setLore("\n §8» §f§lInformations: \n \n §8▪ §7Grade: §f? \n §8▪ §7JKCoins: §f? \n \n§6§l» §eClique: §fPlus d'informations."));


    }
}

