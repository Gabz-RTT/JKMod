package fr.jkmc.mod.utils.guis;


import fr.jkmc.mod.Mod;
import fr.jkmc.mod.utils.CustomItemStack;
import fr.jkmc.mod.utils.guimanager.Menu;
import fr.jkmc.mod.utils.guimanager.PlayerMenuUtility;
import fr.jkmc.mod.utils.guis.ban.BanMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SSMenu extends Menu {

    private Integer[] glass = {0, 1, 7, 8, 9, 17, 36, 44, 45, 46, 52, 53};


    public SSMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.WHITE + playerMenuUtility.getPlayerToSS().getName() + " §8» §cSanctions";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();


         if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cFermer l'inventaire")) {
            player.closeInventory();

         }else if(e.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)){
             if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §fBannissement(s)")){
                 new BanMenu(Mod.getPlayerMenuUtility(player)).open();
             }
        }
    }

    @Override
    public void setMenuItems() {

        for (int i : glass) {
            inventory.setItem(i, new CustomItemStack(Material.RED_STAINED_GLASS_PANE).setName("§f"));
        }

        ItemStack playerHead1 = new ItemStack(Material.LEGACY_SKULL_ITEM);
        SkullMeta skullMeta = (SkullMeta) playerHead1.getItemMeta();
        skullMeta.setOwner(playerMenuUtility.getPlayerToSS().getName());
        playerHead1.setItemMeta(skullMeta);

        inventory.setItem(4, new CustomItemStack(playerHead1).setName("§8» §f" + playerMenuUtility.getPlayerToSS().getName()).setLore("\n §8» §f§lInformations: \n \n §8▪ §7Grade: §f? \n §8▪ §7JKCoins: §f? \n \n§6§l» §eClique: §fPlus d'informations."));

        inventory.setItem(21, new CustomItemStack(Material.DIAMOND_SWORD).setName("§8» §fBannissement(s)").setLore("\n §8▪ §7En cours d'écriture... \n \n§6§l» §eClique: §fOuvrir."));
        inventory.setItem(22, new CustomItemStack(Material.DIAMOND_AXE).setName("§8» §fMute(s)").setLore("\n §8▪ §7En cours d'écriture... \n \n§6§l» §eClique: §fOuvrir."));
        inventory.setItem(23, new CustomItemStack(Material.DIAMOND_SHOVEL).setName("§8» §fExpulsion(s)").setLore("\n §8▪ §7En cours d'écriture... \n \n§6§l» §eClique: §fOuvrir."));

        inventory.setItem(49, makeItem(Material.LEGACY_DARK_OAK_DOOR_ITEM, "§8» §cFermer l'inventaire"));



    }
}

