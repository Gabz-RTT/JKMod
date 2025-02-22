package fr.jkmc.mod.utils.guimanager;

import org.bukkit.Material;


public abstract class PaginatedMenu extends Menu {


    protected int page = 0;
    protected int maxItemsPerPage = 28;
    protected int index = 0;

    private Integer[] glass = {0, 1, 7, 8, 9, 17, 36, 44, 45, 46, 52, 53};

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }


    public void addMenuBorder(){

        inventory.setItem(48, makeItem(Material.ARROW, "§8» §aPage Précédente"));

        inventory.setItem(49, makeItem(Material.LEGACY_DARK_OAK_DOOR_ITEM, "§8» §cFermer l'inventaire"));

        inventory.setItem(50, makeItem(Material.ARROW, "§8» §aPage Suivante"));


        for (int i : glass) {
            inventory.setItem(i, super.FILLER_GLASS);
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
}


