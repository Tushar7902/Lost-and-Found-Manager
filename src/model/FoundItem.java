package model;

public class FoundItem extends Item {

    private int finderId;
    private String status;

    public FoundItem(int itemId, int finderId, String name, String category,
                     String description, String color,
                     String location, String date) {

        super(itemId, name, category, description, color, location, date);

        this.finderId = finderId;
        this.status = "FOUND";
    }

    public int getFinderId() {
        return finderId;
    }

    public String getStatus() {
        return status;
    }

    public void markAsClaimed() {
        status = "CLAIMED";
    }

    @Override
    public String toString() {
        return "----- FOUND ITEM -----\n" +
                super.toString() +
                "\nFinder ID: " + finderId +
                "\nStatus: " + status;
    }
}