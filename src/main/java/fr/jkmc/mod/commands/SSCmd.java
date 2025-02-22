package fr.jkmc.mod.commands;

import fr.jkmc.mod.Mod;
import fr.jkmc.mod.guis.SSGui;
import fr.jkmc.mod.manager.PlayerModManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SSCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande !");
            return true;
        }
        Player player = (Player) sender;

        if (player.hasPermission("jkstaff.ss")) {
            if (args.length == 0) {
                player.sendMessage("§c§lJKSTAFF §f┃ §cErreur: /ss <joueur>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                player.sendMessage(ChatColor.RED + "Le joueur '" + args[0] + "' n'est pas connecté !");
                return true;
            }

            new SSGui(player, target).open(player);
            return true;
        }
        return false;
    }


}

