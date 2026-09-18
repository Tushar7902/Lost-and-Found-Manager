package model;
import java.io.Serializable;
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int itemId;
    protected String name;
    protected String category;
    protected String description;
    protected String color;
    protected String location;
    protected String date;
    public Item(int itemId, String name, String category,
                String description, String color,
                String location, String date) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.color = color;
        this.location = location;
        this.date = date;
    }
    public int getItemId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public String getColor() {
        return color;
    }
    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
    @Override
    public String toString() {
        return "Item ID: " + itemId +
                "\nName: " + name +
                "\nCategory: " + category +
                "\nDescription: " + description +
                "\nColor: " + color +
                "\nLocation: " + location +
                "\nDate: " + date;
    }
}
