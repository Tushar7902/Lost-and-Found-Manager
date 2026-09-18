package service;

import model.LostItem;
import model.FoundItem;

import java.util.ArrayList;

public class ItemService {

    private ArrayList<LostItem> lostItems;
    private ArrayList<FoundItem> foundItems;

    public ItemService() {
        lostItems = new ArrayList<>();
        foundItems = new ArrayList<>();
    }

    public void addLostItem(LostItem item) {
        lostItems.add(item);
        System.out.println("Lost item reported successfully!");
    }

    public void addFoundItem(FoundItem item) {
        foundItems.add(item);
        System.out.println("Found item reported successfully!");
    }

    public void viewLostItems() {
        if (lostItems.isEmpty()) {
            System.out.println("No lost items reported.");
            return;
        }

        System.out.println("\n===== LOST ITEMS =====");

        for (LostItem item : lostItems) {
            System.out.println(item);
            System.out.println("----------------------");
        }
    }

    public void viewFoundItems() {
        if (foundItems.isEmpty()) {
            System.out.println("No found items reported.");
            return;
        }

        System.out.println("\n===== FOUND ITEMS =====");

        for (FoundItem item : foundItems) {
            System.out.println(item);
            System.out.println("----------------------");
        }
    }

    public LostItem findLostItemById(int itemId) {
        for (LostItem item : lostItems) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public FoundItem findFoundItemById(int itemId) {
        for (FoundItem item : foundItems) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<LostItem> getLostItems() {
        return lostItems;
    }

    public ArrayList<FoundItem> getFoundItems() {
        return foundItems;
    }

    public void setLostItems(ArrayList<LostItem> lostItems) {
        this.lostItems = lostItems;
    }

    public void setFoundItems(ArrayList<FoundItem> foundItems) {
        this.foundItems = foundItems;
    }
}