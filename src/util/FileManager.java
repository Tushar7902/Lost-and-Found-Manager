package util;

import model.User;
import model.LostItem;
import model.FoundItem;
import model.Claim;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String USERS_FILE =
            DATA_FOLDER + File.separator + "users.dat";

    private static final String LOST_FILE =
            DATA_FOLDER + File.separator + "lost_items.dat";

    private static final String FOUND_FILE =
            DATA_FOLDER + File.separator + "found_items.dat";

    private static final String CLAIMS_FILE =
            DATA_FOLDER + File.separator + "claims.dat";

    public static void initializeDataFolder() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void saveUsers(ArrayList<User> users) {
        saveObject(users, USERS_FILE);
    }

    public static void saveLostItems(ArrayList<LostItem> items) {
        saveObject(items, LOST_FILE);
    }

    public static void saveFoundItems(ArrayList<FoundItem> items) {
        saveObject(items, FOUND_FILE);
    }

    public static void saveClaims(ArrayList<Claim> claims) {
        saveObject(claims, CLAIMS_FILE);
    }

    public static ArrayList<User> loadUsers() {

        Object object = loadObject(USERS_FILE);

        if (object instanceof ArrayList<?>) {
            return (ArrayList<User>) object;
        }

        return new ArrayList<>();
    }

    public static ArrayList<LostItem> loadLostItems() {

        Object object = loadObject(LOST_FILE);

        if (object instanceof ArrayList<?>) {
            return (ArrayList<LostItem>) object;
        }

        return new ArrayList<>();
    }

    public static ArrayList<FoundItem> loadFoundItems() {

        Object object = loadObject(FOUND_FILE);

        if (object instanceof ArrayList<?>) {
            return (ArrayList<FoundItem>) object;
        }

        return new ArrayList<>();
    }

    public static ArrayList<Claim> loadClaims() {

        Object object = loadObject(CLAIMS_FILE);

        if (object instanceof ArrayList<?>) {
            return (ArrayList<Claim>) object;
        }

        return new ArrayList<>();
    }

    private static void saveObject(Object object, String fileName) {

        initializeDataFolder();

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(fileName))) {

            output.writeObject(object);

        } catch (IOException e) {

            System.out.println(
                    "Warning: Could not save data."
            );
        }
    }

    private static Object loadObject(String fileName) {

        File file = new File(fileName);

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            return input.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Warning: Could not load saved data."
            );

            return null;
        }
    }
}