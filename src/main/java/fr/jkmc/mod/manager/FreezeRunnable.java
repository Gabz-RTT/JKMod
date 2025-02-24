package fr.jkmc.mod.manager;

import fr.jkmc.mod.Mod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class FreezeRunnable extends BukkitRunnable {

    private int t = 0;

    @Override
    public void run() {
        for(UUID uuid : Mod.getInstance().getFrozenPlayers().keySet()){
            Player player = Bukkit.getPlayer(uuid);
            if(player != null){
                player.teleport(Mod.getInstance().getFrozenPlayers().get(uuid));
                if(t == 5){
                    player.sendMessage("§c§lJKSTAFF §f┃ §cVous avez été freeze !");
                }
            }
        }

        if(t == 5){
            t = 0;
        }

        t++;
    }
}
