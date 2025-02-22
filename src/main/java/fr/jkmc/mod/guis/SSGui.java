package fr.jkmc.mod.guis;

import fr.jkmc.mod.utils.CustomGui;
import fr.jkmc.mod.utils.CustomItemStack;
import fr.jkmc.mod.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class SSGui extends CustomGui  {

    private Integer[] glass = {0, 1, 7, 8, 9, 17, 36, 44, 45, 46, 52, 53};


    public SSGui(Player player, Player target) {
        super(6, ChatColor.WHITE + target.getName() + " §8» §cSanctions", player);

        ItemStack playerHead1 = new ItemStack(Material.LEGACY_SKULL_ITEM);
        SkullMeta skullMeta = (SkullMeta) playerHead1.getItemMeta();
        skullMeta.setOwner(target.getName());
        playerHead1.setItemMeta(skullMeta);

        setItem(4, new CustomItemStack(playerHead1).setName("§8» §f" + target.getName()).setLore("\n§8» §f§lInformations: \n \n §8▪ §7Grade: §f? \n §8▪ §7JKCoins: §f? \n \n§6§l» §eClique: §fPlus d'informations."));

        setItem(22, new CustomItemStack(Material.DIAMOND_SWORD).setName("§8» §fBannissement(s)").setLore("\n §8▪ §7En cours d'écriture... \n \n§6§l» §eClique: §fOuvrir."));
        setItem(22, new CustomItemStack(Material.DIAMOND_AXE).setName("§8» §fMute(s)").setLore("\n §8▪ §7En cours d'écriture... \n \n§6§l» §eClique: §fOuvrir."));
        setItem(23, new CustomItemStack(Material.DIAMOND_SHOVEL).setName("§8» §fExpulsion(s)").setLore("\n §8▪ §7En cours d'écriture... \n \n§6§l» §eClique: §fOuvrir."));


        setItem(49, new CustomItemStack(Material.LEGACY_DARK_OAK_DOOR_ITEM).setName("§8» §cFermer l'inventaire"), player1 -> {
            player1.closeInventory();
        });

        for (int i : glass) {
            setItem(i, new CustomItemStack(Material.RED_STAINED_GLASS_PANE).setName("§f"));
        }

    }


}
