package fr.jkmc.mod.listeners;

import fr.jkmc.mod.Mod;
import fr.jkmc.mod.manager.PlayerModManager;
import fr.jkmc.mod.utils.guimanager.PlayerMenuUtility;
import fr.jkmc.mod.utils.guis.SSMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModItemInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if(!PlayerModManager.isInModerationMod(player)) return;
        if(!(e.getRightClicked() instanceof  Player)) return;
        Player target = (Player) e.getRightClicked();

        e.setCancelled(true);

        switch(player.getInventory().getItemInHand().getType()){

            case PAPER:
                Inventory inv = Bukkit.createInventory(null, 5 * 9, target.getName() + " » Inv");

                for(int i = 0; i < 36; i++){
                    if(target.getInventory().getItem(i) != null){
                        inv.setItem(i, target.getInventory().getItem(i));
                    }
                }

                inv.setItem(36, target.getInventory().getHelmet());
                inv.setItem(37, target.getInventory().getChestplate());
                inv.setItem(38, target.getInventory().getLeggings());
                inv.setItem(39, target.getInventory().getBoots());

                player.openInventory(inv);
                break;

            case BOOK:
                PlayerMenuUtility playerMenuUtility = Mod.getPlayerMenuUtility(player);
                playerMenuUtility.setPlayerToSS(target);

                new SSMenu(Mod.getPlayerMenuUtility(player)).open();
                break;


            case PACKED_ICE:
                if(Mod.getInstance().getFrozenPlayers().containsKey(target.getUniqueId())){
                    Mod.getInstance().getFrozenPlayers().remove(target.getUniqueId());
                    player.sendMessage("§c§lJKSTAFF §f┃ §cVous avez unfreeze §f" + target.getName());
                } else {
                    Mod.getInstance().getFrozenPlayers().put(target.getUniqueId(), target.getLocation());
                    player.sendMessage("§c§lJKSTAFF §f┃ §cVous avez freeze §f" + target.getName());
                }
                break;



            case BLAZE_ROD:
                target.damage(target.getHealth());
                break;

            default: break;
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(!PlayerModManager.isInModerationMod(player)) return;
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;

        switch(player.getInventory().getItemInHand().getType()){

            case ARROW:
                List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                list.remove(player);

                if(list.size() == 0){
                    player.sendMessage("§c§lJKSTAFF §f┃ §cIl n'y a aucun Joueur.");
                    return;
                }

                Player target = list.get(new Random().nextInt(list.size()));
                player.teleport(target.getLocation());
                player.sendMessage("§c§lJKSTAFF §f┃ §cVous avez été téléporté à §f" + target.getName());
                break;


            case LIME_CONCRETE_POWDER:
                PlayerModManager mod = PlayerModManager.getFromPlayer(player);
                mod.setVanished(!mod.isVanished());
                player.sendMessage(mod.isVanished() ? "§c§lJKSTAFF §f┃ §aVous êtes Maintenant Invisible." : "§c§lJKSTAFF §f┃ §cAttention, Vous êtes de nouveau Visible !");
                break;

            default: break;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        for(Player players : Bukkit.getOnlinePlayers()){
            if(PlayerModManager.isInModerationMod(players)){
                PlayerModManager pm = PlayerModManager.getFromPlayer(players);
                if(pm.isVanished()){
                    player.hidePlayer(players);
                }
            }
        }
    }
}
