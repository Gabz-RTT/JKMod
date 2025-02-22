package fr.jkmc.mod.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChat implements Listener {

    @EventHandler
    public void staffChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (player.hasPermission("staffchat.use")) {
            if (message.startsWith("@")) {
                event.setCancelled(true);

                String staffMessage = "§c§lJKSTAFF §f┃ §c" + player.getName() + " §8» §f" + message.substring(1);
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.hasPermission("staffchat.use")) {
                        onlinePlayer.sendMessage(staffMessage);
                    }
                }
            }
        }
    }
}
