package fr.jkmc.mod.listeners;

import fr.jkmc.mod.Mod;
import fr.jkmc.mod.manager.PlayerModManager;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ProtectinMOD implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        e.setCancelled(PlayerModManager.isInModerationMod(e.getPlayer()) || Mod.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(PlayerModManager.isInModerationMod(e.getPlayer()) || Mod.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(PlayerModManager.isInModerationMod(e.getPlayer()) || Mod.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        e.setCancelled(PlayerModManager.isInModerationMod(e.getPlayer()) || Mod.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        e.setCancelled(PlayerModManager.isInModerationMod((Player) e.getEntity()) || Mod.getInstance().isFreeze((Player) e.getEntity()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        e.setCancelled(PlayerModManager.isInModerationMod(e.getPlayer()) || Mod.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        e.setCancelled(PlayerModManager.isInModerationMod((Player) e.getWhoClicked()) || Mod.getInstance().isFreeze((Player) e.getWhoClicked()));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(Mod.getInstance().isFreeze(e.getPlayer())){
            e.setTo(e.getFrom());
        }
    }
}
