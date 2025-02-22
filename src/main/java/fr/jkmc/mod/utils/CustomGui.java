package fr.jkmc.mod.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class CustomGui {
    public static Map<UUID, CustomGui> inventoriesByUUID = new HashMap();
    public static Map<UUID, UUID> openInventories = new HashMap();
    private UUID uuid = UUID.randomUUID();
    private Inventory yourInventory;
    private Map<Integer, CustomGuiAction> actions;
    private int line;

    public CustomGui(int line, String invName, Player player) {
        this.yourInventory = Bukkit.createInventory((InventoryHolder)null, line * 9, invName);
        this.line = line;
        this.actions = new HashMap();
        inventoriesByUUID.put(this.getUuid(), this);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public int getLine() {
        return this.line;
    }

    void onClick(Player paramPlayer, Inventory paramInventory, ItemStack paramItemStack, int paramInt, ClickType paramClickType) {
    }

    public void setItem(int slot, ItemStack stack, CustomGuiAction action) {
        this.yourInventory.setItem(slot, stack);
        if (action != null) {
            this.actions.put(slot, action);
        }

    }

    public void setItem(int slot, ItemStack stack) {
        this.setItem(slot, stack, (CustomGuiAction)null);
    }

    public void open(Player p) {
        p.openInventory(this.yourInventory);
        openInventories.put(p.getUniqueId(), this.getUuid());
    }

    public static Map<UUID, CustomGui> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }

    public Map<Integer, CustomGuiAction> getActions() {
        return this.actions;
    }

    public void delete() {
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while(var1.hasNext()) {
            Player p = (Player)var1.next();
            UUID u = (UUID)openInventories.get(p.getUniqueId());
            if (u.equals(this.getUuid())) {
                p.closeInventory();
            }
        }

        inventoriesByUUID.remove(this.getUuid());
    }

    public interface CustomGuiAction {
        void click(Player var1);
    }
}
