package fr.jkmc.mod.manager;

import fr.jkmc.mod.Mod;
import fr.jkmc.mod.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerModManager {

    private Player player;
    private ItemStack[] items = new ItemStack[40];
    private boolean vanished;

    public PlayerModManager(Player player){
        this.player = player;
        vanished = false;
    }

    public void init(){
        Mod.getInstance().players.put(player.getUniqueId(), this);
    }

    public void giveItem(Player player){
        ItemBuilder invSee = new ItemBuilder(Material.PAPER).setName("§f» §cVoir l'inventaire").setLore(" §f• §7Clique pour voir l'inventaire du Joueur.");
        ItemBuilder ss = new ItemBuilder(Material.BOOK).setName("§f» §cSS").setLore(" §f• §7Clique pour appliquer une sanction.");
        ItemBuilder freeze = new ItemBuilder(Material.PACKED_ICE).setName("§f» §cFreeze").setLore(" §f• §7Clique pour geler le Joueur.");

        ItemBuilder vanish = new ItemBuilder(Material.LIME_CONCRETE_POWDER).setName("§f» §aVanish").setLore(" §f• §7Clique pour te rendre Visible/Invisible.");

        ItemBuilder kbTester = new ItemBuilder(Material.STICK).setName("§f» §cTest de recul").setLore(" §f• §7Clique pour tester le recul du Joueur.").addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
        ItemBuilder killer = new ItemBuilder(Material.BLAZE_ROD).setName("§f» §cTueur de joueur").setLore(" §f• §7Clique pour tuer le Joueur.");
        ItemBuilder tpRandom = new ItemBuilder(Material.ARROW).setName("§f» §cTéléportation aléatoire").setLore(" §f• §7Clique pour te téléporter Aléatoirement.");

        player.getInventory().setItem(0, invSee.toItemStack());
        player.getInventory().setItem(1, ss.toItemStack());
        player.getInventory().setItem(2, freeze.toItemStack());

        player.getInventory().setItem(4, vanish.toItemStack());

        player.getInventory().setItem(6, kbTester.toItemStack());
        player.getInventory().setItem(7, killer.toItemStack());
        player.getInventory().setItem(8, tpRandom.toItemStack());

    }

    public void destroy(){
        Mod.getInstance().players.remove(player.getUniqueId());
    }

    public static PlayerModManager getFromPlayer(Player player){
        return Mod.getInstance().players.get(player.getUniqueId());
    }

    public static boolean isInModerationMod(Player player){
        return Mod.getInstance().mods.contains(player.getUniqueId());
    }

    public ItemStack[] getItems() {
        return items;
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished){
        this.vanished = vanished;
        if(vanished){
            Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));
        } else {
            Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));
        }
    }

    public void saveInventory(){
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = player.getInventory().getItem(slot);
            if(item != null){
                items[slot] = item;
            }
        }

        items[36] = player.getInventory().getHelmet();
        items[37] = player.getInventory().getChestplate();
        items[38] = player.getInventory().getLeggings();
        items[39] = player.getInventory().getBoots();

        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public void giveInventory(){
        player.getInventory().clear();

        for(int slot = 0; slot < 36; slot++){
            ItemStack item = items[slot];
            if(item != null){
                player.getInventory().setItem(slot, item);
            }
        }

        player.getInventory().setHelmet(items[36]);
        player.getInventory().setChestplate(items[37]);
        player.getInventory().setLeggings(items[38]);
        player.getInventory().setBoots(items[39]);
    }
}
