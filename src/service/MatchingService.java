package service;

import model.LostItem;
import model.FoundItem;

import java.util.ArrayList;

public class MatchingService {

    public void findMatches(ItemService itemService, int lostItemId) {

        LostItem lostItem = itemService.findLostItemById(lostItemId);

        if (lostItem == null) {
            System.out.println("Lost item not found.");
            return;
        }

        ArrayList<FoundItem> foundItems = itemService.getFoundItems();

        if (foundItems.isEmpty()) {
            System.out.println("No found items available for matching.");
            return;
        }

        boolean matchFound = false;

        System.out.println("\n===== POSSIBLE MATCHES =====");

        for (FoundItem foundItem : foundItems) {

            if (foundItem.getStatus().equals("CLAIMED")) {
                continue;
            }

            int score = calculateMatchScore(lostItem, foundItem);

            if (score >= 50) {

                matchFound = true;

                System.out.println("\nFound Item ID: " + foundItem.getItemId());
                System.out.println("Item Name: " + foundItem.getName());
                System.out.println("Category: " + foundItem.getCategory());
                System.out.println("Color: " + foundItem.getColor());
                System.out.println("Location: " + foundItem.getLocation());
                System.out.println("Match Score: " + score + "%");
                System.out.println("----------------------------");
            }
        }

        if (!matchFound) {
            System.out.println("No possible matches found.");
        }
    }

    private int calculateMatchScore(LostItem lost, FoundItem found) {

        int score = 0;

        if (lost.getCategory().equalsIgnoreCase(found.getCategory())) {
            score += 25;
        }

        if (lost.getColor().equalsIgnoreCase(found.getColor())) {
            score += 20;
        }

        if (lost.getLocation().equalsIgnoreCase(found.getLocation())) {
            score += 25;
        }

        if (lost.getName().equalsIgnoreCase(found.getName())) {
            score += 20;
        }

        if (lost.getDescription().equalsIgnoreCase(found.getDescription())) {
            score += 10;
        }

        return score;
    }
}