package fr.jkmc.mod.commands;

import fr.jkmc.mod.Mod;
import fr.jkmc.mod.manager.PlayerModManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("! Seul un joueur peut executer cette commande !");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("jkstaff.mod")) {
            player.sendMessage("§c§lJKSTAFF §f┃ §cVous n'avez pas la permission d'éxecuter cette commande !");
            return false;
        }

        if (Mod.getInstance().mods.contains(player.getUniqueId())) {
            PlayerModManager pm = PlayerModManager.getFromPlayer(player);
            Mod.getInstance().mods.remove(player.getUniqueId());

            player.getInventory().clear();
            player.sendMessage("§c§lJKSTAFF §f┃ §cMode modération désactivé !");
            player.setAllowFlight(false);
            player.setFlying(false);
            pm.giveInventory();
            pm.destroy();
            return false;
        }

        PlayerModManager pm = new PlayerModManager(player);
        pm.init();

        Mod.getInstance().mods.add(player.getUniqueId());
        player.setAllowFlight(true);
        player.setFlying(true);
        player.sendMessage("§c§lJKSTAFF §f┃ §aVous venez de rentrer dans le Mode Modération !");
        pm.saveInventory();
        pm.giveItem(player);

        return false;
    }
}
