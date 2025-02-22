package fr.jkmc.mod.utils.guimanager;

import org.bukkit.entity.Player;


public class PlayerMenuUtility {

    private Player owner;
    private Player playerToSS;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getPlayerToSS() {
        return playerToSS;
    }

    public void setPlayerToSS(Player playerToSS) {
        this.playerToSS = playerToSS;
    }
}


