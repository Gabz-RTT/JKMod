package fr.jkmc.mod.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomItemStack extends ItemStack {
    public static CustomItemStack getBackGuiItem() {
        return (new CustomItemStack(Material.ARROW)).setName("§aRetour");
    }

    public static CustomItemStack getCloseGuiItem() {
        return (new CustomItemStack(Material.BARRIER)).setName("§cFermer");
    }

    public CustomItemStack(ItemStack item, String displayName, String lore) {
        super(item);
    }

    public CustomItemStack(ItemStack item, String displayName) {
        super(item);
    }

    public CustomItemStack(ItemStack item) {
        super(item);
    }

    public CustomItemStack(Material material, int amount, byte data) {
        super(material, amount, (short)data);
    }

    public CustomItemStack(Material material, int amount) {
        super(material, amount);
    }

    public CustomItemStack(Material material) {
        super(material);
    }

    public CustomItemStack setLore(String lore) {
        if (lore != null && !lore.equals("")) {
            ItemMeta meta = this.getItemMeta();
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_DESTROYS});
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_PLACED_ON});
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
            lore = ChatColor.translateAlternateColorCodes('&', lore);
            String[] loreString = lore.split("\n");
            meta.setLore(new ArrayList(Arrays.asList(loreString)));
            this.setItemMeta(meta);
            return this;
        } else {
            return this;
        }
    }

    public CustomItemStack setName(String displayName) {
        if (displayName != null && !displayName.equals("")) {
            ItemMeta meta = this.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
            this.setItemMeta(meta);
            return this;
        } else {
            return this;
        }
    }

    public CustomItemStack setLeatherColor(Color color) {
        LeatherArmorMeta lch = (LeatherArmorMeta)this.getItemMeta();
        lch.setColor(color);
        this.setItemMeta(lch);
        return this;
    }

    public CustomItemStack setLoreAction(String actionName) {
        if (actionName.startsWith("§")) {
            this.addLineToLore("§7", actionName);
        } else {
            this.addLineToLore("§7", "§6§l» §eCliquez pour " + actionName);
        }

        return this;
    }

    public CustomItemStack setCustomAmount(int amount) {
        this.setAmount(amount);
        return this;
    }

    public CustomItemStack setData(byte data) {
        this.setDurability((short)data);
        return this;
    }


    public CustomItemStack setUnbreakable() {
        ItemMeta meta = this.getItemMeta();
        meta.setUnbreakable(true);
        this.setItemMeta(meta);
        return this;
    }

    public CustomItemStack setUnbreakable(boolean enabled) {
        ItemMeta meta = this.getItemMeta();
        meta.setUnbreakable(enabled);
        this.setItemMeta(meta);
        return this;
    }




    public CustomItemStack addCustomEnchantment(Enchantment enchantment, int level) {
        super.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public void addLineToLore(String... lines) {
        ItemMeta meta = this.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList();
        }

        int i = -1;
        String[] var5 = lines;
        int var6 = lines.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String line = var5[var7];
            ++i;
            if (i != 0 || line != null || this.hasAlreadyLore()) {
                ((List)lore).add(line == null ? "" : line);
            }
        }

        meta.setLore((List)lore);
        this.setItemMeta(meta);
    }

    private boolean hasAlreadyLore() {
        ItemMeta meta = this.getItemMeta();
        if (meta == null) {
            return false;
        } else {
            List<String> lore = meta.getLore();
            return lore != null && lore.size() > 0;
        }
    }


}
