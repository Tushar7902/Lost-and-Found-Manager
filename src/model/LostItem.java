package model;

public class LostItem extends Item {

    private int userId;
    private String status;

    public LostItem(int itemId, int userId, String name, String category,
                    String description, String color,
                    String location, String date) {

        super(itemId, name, category, description, color, location, date);

        this.userId = userId;
        this.status = "LOST";
    }

    public int getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public void markAsFound() {
        status = "FOUND";
    }

    @Override
    public String toString() {
        return "----- LOST ITEM -----\n" +
                super.toString() +
                "\nUser ID: " + userId +
                "\nStatus: " + status;
    }
}