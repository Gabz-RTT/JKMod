package fr.jkmc.mod;

import fr.jkmc.mod.commands.ModCMD;
import fr.jkmc.mod.commands.SSCmd;
import fr.jkmc.mod.listeners.CustomGuiListener;
import fr.jkmc.mod.listeners.ModItemInteract;
import fr.jkmc.mod.listeners.ProtectinMOD;
import fr.jkmc.mod.listeners.StaffChat;
import fr.jkmc.mod.manager.FreezeRunnable;
import fr.jkmc.mod.manager.PlayerModManager;
import fr.jkmc.mod.utils.guimanager.MenuListener;
import fr.jkmc.mod.utils.guimanager.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Mod extends JavaPlugin {

    private static Mod Instance;

    private Map<UUID, Location> frozenPlayers;

    public ArrayList<UUID> mods = new ArrayList<>();
    public HashMap<UUID, PlayerModManager> players = new HashMap<>();

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        mods = new ArrayList<>();
        players = new HashMap<>();
        frozenPlayers = new HashMap<>();

        new FreezeRunnable().runTaskTimer(this, 0, 20);

        Instance = this;

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ModItemInteract(), this);
        pm.registerEvents(new StaffChat(), this);
        pm.registerEvents(new CustomGuiListener(), this);
        pm.registerEvents(new ProtectinMOD(), this);
        pm.registerEvents(new MenuListener(), this);

        getCommand("mod").setExecutor(new ModCMD());
        getCommand("ss").setExecutor(new SSCmd());
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) {

            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p);
        }
    }


    @Override
    public void onDisable() {

    }

    public static Mod getInstance() {
        return Instance;
    }
    public boolean isFreeze(Player player){
        return getFrozenPlayers().containsKey(player.getUniqueId());
    }

    public Map<UUID, Location> getFrozenPlayers() {
        return frozenPlayers;
    }

    public ArrayList<UUID> getMods() {
        return mods;
    }

    public HashMap<UUID, PlayerModManager> getPlayers() {
        return players;
    }
}
