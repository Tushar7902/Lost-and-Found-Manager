import model.User;
import model.LostItem;
import model.FoundItem;

import service.UserService;
import service.ItemService;
import service.MatchingService;
import service.ClaimService;

import util.FileManager;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static UserService userService = new UserService();
    private static ItemService itemService = new ItemService();
    private static MatchingService matchingService = new MatchingService();
    private static ClaimService claimService = new ClaimService();

    public static void main(String[] args) {

        loadSavedData();

        boolean running = true;

        System.out.println("\n======================================");
        System.out.println("        LOST & FOUND MANAGER");
        System.out.println("======================================");
        System.out.println("  A Java-based Lost & Found System");
        System.out.println("======================================");

        while (running) {

            showMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    reportLostItem();
                    break;

                case 3:
                    reportFoundItem();
                    break;

                case 4:
                    itemService.viewLostItems();
                    break;

                case 5:
                    itemService.viewFoundItems();
                    break;

                case 6:
                    searchItems();
                    break;

                case 7:
                    findMatches();
                    break;

                case 8:
                    manageClaims();
                    break;

                case 9:
                    userService.viewUsers();
                    break;

                case 10:
                    saveAllData();
                    System.out.println(
                            "\nThank you for using Lost & Found Manager!"
                    );
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-10."
                    );
            }
        }

        scanner.close();
    }

    // ================= DATA =================

    private static void loadSavedData() {

        FileManager.initializeDataFolder();

        userService.setUsers(
                FileManager.loadUsers()
        );

        itemService.setLostItems(
                FileManager.loadLostItems()
        );

        itemService.setFoundItems(
                FileManager.loadFoundItems()
        );

        claimService.setClaims(
                FileManager.loadClaims()
        );
    }

    private static void saveAllData() {

        FileManager.saveUsers(
                userService.getUsers()
        );

        FileManager.saveLostItems(
                itemService.getLostItems()
        );

        FileManager.saveFoundItems(
                itemService.getFoundItems()
        );

        FileManager.saveClaims(
                claimService.getClaims()
        );
    }

    // ================= MENU =================

    private static void showMenu() {

        System.out.println("\n======================================");
        System.out.println("              MAIN MENU");
        System.out.println("======================================");
        System.out.println("1. Register User");
        System.out.println("2. Report Lost Item");
        System.out.println("3. Report Found Item");
        System.out.println("4. View Lost Items");
        System.out.println("5. View Found Items");
        System.out.println("6. Search Items");
        System.out.println("7. Find Possible Matches");
        System.out.println("8. Manage Claims");
        System.out.println("9. View Users");
        System.out.println("10. Exit");
        System.out.println("======================================");
    }

    // ================= USER =================

    private static void registerUser() {

        System.out.println("\n===== REGISTER USER =====");

        int id = readPositiveInt("Enter User ID: ");

        if (userService.findUserById(id) != null) {
            System.out.println(
                    "A user with this ID already exists."
            );
            return;
        }

        System.out.print("Enter Name: ");
        String name = readNonEmpty();

        System.out.print("Enter Email: ");
        String email = readNonEmpty();

        System.out.print("Enter Phone: ");
        String phone = readNonEmpty();

        User user = new User(
                id,
                name,
                email,
                phone
        );

        userService.addUser(user);

        saveAllData();
    }

    // ================= LOST ITEM =================

    private static void reportLostItem() {

        System.out.println("\n===== REPORT LOST ITEM =====");

        int itemId = readPositiveInt("Enter Item ID: ");
        int userId = readPositiveInt("Enter Your User ID: ");

        if (userService.findUserById(userId) == null) {

            System.out.println(
                    "User not found. Please register first."
            );

            return;
        }

        if (itemService.findLostItemById(itemId) != null
                || itemService.findFoundItemById(itemId) != null) {

            System.out.println(
                    "An item with this ID already exists."
            );

            return;
        }

        System.out.print("Enter Item Name: ");
        String name = readNonEmpty();

        String category = selectCategory();

        System.out.print("Enter Description: ");
        String description = readNonEmpty();

        String color = selectColor();

        String location = selectLocation();

        System.out.print("Enter Date Lost (DD-MM-YYYY): ");
        String date = readNonEmpty();

        LostItem item = new LostItem(
                itemId,
                userId,
                name,
                category,
                description,
                color,
                location,
                date
        );

        itemService.addLostItem(item);

        saveAllData();
    }

    // ================= FOUND ITEM =================

    private static void reportFoundItem() {

        System.out.println("\n===== REPORT FOUND ITEM =====");

        int itemId = readPositiveInt("Enter Item ID: ");
        int finderId = readPositiveInt("Enter Your User ID: ");

        if (userService.findUserById(finderId) == null) {

            System.out.println(
                    "User not found. Please register first."
            );

            return;
        }

        if (itemService.findLostItemById(itemId) != null
                || itemService.findFoundItemById(itemId) != null) {

            System.out.println(
                    "An item with this ID already exists."
            );

            return;
        }

        System.out.print("Enter Item Name: ");
        String name = readNonEmpty();

        String category = selectCategory();

        System.out.print("Enter Description: ");
        String description = readNonEmpty();

        String color = selectColor();

        String location = selectLocation();

        System.out.print("Enter Date Found (DD-MM-YYYY): ");
        String date = readNonEmpty();

        FoundItem item = new FoundItem(
                itemId,
                finderId,
                name,
                category,
                description,
                color,
                location,
                date
        );

        itemService.addFoundItem(item);

        saveAllData();
    }

    // ================= CATEGORY =================

    private static String selectCategory() {

        while (true) {

            System.out.println("\n===== SELECT CATEGORY =====");
            System.out.println("1. Electronics");
            System.out.println("2. Documents / ID");
            System.out.println("3. Books / Stationery");
            System.out.println("4. Clothing");
            System.out.println("5. Accessories");
            System.out.println("6. Keys");
            System.out.println("7. Bags");
            System.out.println("8. Other");

            int choice =
                    readInt("Enter category choice: ");

            switch (choice) {

                case 1:
                    return "Electronics";

                case 2:
                    return "Documents / ID";

                case 3:
                    return "Books / Stationery";

                case 4:
                    return "Clothing";

                case 5:
                    return "Accessories";

                case 6:
                    return "Keys";

                case 7:
                    return "Bags";

                case 8:
                    System.out.print(
                            "Enter your category: "
                    );
                    return readNonEmpty();

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-8."
                    );
            }
        }
    }

    // ================= COLOR =================

    private static String selectColor() {

        while (true) {

            System.out.println("\n===== SELECT COLOR =====");
            System.out.println("1. Black");
            System.out.println("2. White");
            System.out.println("3. Blue");
            System.out.println("4. Red");
            System.out.println("5. Green");
            System.out.println("6. Yellow");
            System.out.println("7. Grey");
            System.out.println("8. Brown");
            System.out.println("9. Other");

            int choice =
                    readInt("Enter color choice: ");

            switch (choice) {

                case 1:
                    return "Black";

                case 2:
                    return "White";

                case 3:
                    return "Blue";

                case 4:
                    return "Red";

                case 5:
                    return "Green";

                case 6:
                    return "Yellow";

                case 7:
                    return "Grey";

                case 8:
                    return "Brown";

                case 9:
                    System.out.print(
                            "Enter your color: "
                    );
                    return readNonEmpty();

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-9."
                    );
            }
        }
    }

    // ================= LOCATION =================

    private static String selectLocation() {

        while (true) {

            System.out.println("\n===== SELECT LOCATION =====");
            System.out.println("1. Classroom");
            System.out.println("2. Library");
            System.out.println("3. Hostel");
            System.out.println("4. Mess");
            System.out.println("5. Laboratory");
            System.out.println("6. Cafeteria");
            System.out.println("7. Sports Ground");
            System.out.println("8. Parking");
            System.out.println("9. Other");

            int choice =
                    readInt("Enter location choice: ");

            switch (choice) {

                case 1:
                    return "Classroom";

                case 2:
                    return "Library";

                case 3:
                    return "Hostel";

                case 4:
                    return "Mess";

                case 5:
                    return "Laboratory";

                case 6:
                    return "Cafeteria";

                case 7:
                    return "Sports Ground";

                case 8:
                    return "Parking";

                case 9:
                    System.out.print(
                            "Enter your location: "
                    );
                    return readNonEmpty();

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-9."
                    );
            }
        }
    }

    // ================= SEARCH =================

    private static void searchItems() {

        System.out.println("\n===== SEARCH ITEMS =====");

        System.out.print("Enter keyword: ");

        String keyword =
                scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {

            System.out.println(
                    "Search keyword cannot be empty."
            );

            return;
        }

        boolean found = false;

        for (LostItem item :
                itemService.getLostItems()) {

            if (matchesKeyword(item.getName(), keyword)
                    || matchesKeyword(item.getCategory(), keyword)
                    || matchesKeyword(item.getColor(), keyword)
                    || matchesKeyword(item.getLocation(), keyword)
                    || matchesKeyword(item.getDescription(), keyword)) {

                System.out.println("\n" + item);
                found = true;
            }
        }

        for (FoundItem item :
                itemService.getFoundItems()) {

            if (matchesKeyword(item.getName(), keyword)
                    || matchesKeyword(item.getCategory(), keyword)
                    || matchesKeyword(item.getColor(), keyword)
                    || matchesKeyword(item.getLocation(), keyword)
                    || matchesKeyword(item.getDescription(), keyword)) {

                System.out.println("\n" + item);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No matching items found."
            );
        }
    }

    private static boolean matchesKeyword(
            String text,
            String keyword) {

        return text != null
                && text.toLowerCase().contains(keyword);
    }

    // ================= MATCHING =================

    private static void findMatches() {

        System.out.println(
                "\n===== FIND POSSIBLE MATCHES ====="
        );

        int lostItemId =
                readPositiveInt("Enter Lost Item ID: ");

        matchingService.findMatches(
                itemService,
                lostItemId
        );
    }

    // ================= CLAIMS =================

    private static void manageClaims() {

        System.out.println(
                "\n===== CLAIM MANAGEMENT ====="
        );

        System.out.println("1. Submit Claim");
        System.out.println("2. View Claims");
        System.out.println("3. Approve Claim");
        System.out.println("4. Reject Claim");
        System.out.println("5. Back");

        int choice =
                readInt("Enter choice: ");

        switch (choice) {

            case 1:

                int lostId =
                        readPositiveInt(
                                "Enter Lost Item ID: "
                        );

                int foundId =
                        readPositiveInt(
                                "Enter Found Item ID: "
                        );

                int claimantId =
                        readPositiveInt(
                                "Enter Claimant User ID: "
                        );

                if (userService.findUserById(
                        claimantId) == null) {

                    System.out.println(
                            "User not found."
                    );

                    return;
                }

                if (itemService.findLostItemById(
                        lostId) == null) {

                    System.out.println(
                            "Lost item not found."
                    );

                    return;
                }

                if (itemService.findFoundItemById(
                        foundId) == null) {

                    System.out.println(
                            "Found item not found."
                    );

                    return;
                }

                claimService.createClaim(
                        lostId,
                        foundId,
                        claimantId
                );

                saveAllData();

                break;

            case 2:

                claimService.viewClaims();

                break;

            case 3:

                int approveId =
                        readPositiveInt(
                                "Enter Claim ID: "
                        );

                claimService.approveClaim(
                        approveId,
                        itemService
                );

                saveAllData();

                break;

            case 4:

                int rejectId =
                        readPositiveInt(
                                "Enter Claim ID: "
                        );

                claimService.rejectClaim(
                        rejectId
                );

                saveAllData();

                break;

            case 5:
                break;

            default:
                System.out.println(
                        "Invalid choice."
                );
        }
    }

    // ================= INPUT VALIDATION =================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private static int readPositiveInt(String message) {

        while (true) {

            int value = readInt(message);

            if (value > 0) {
                return value;
            }

            System.out.println(
                    "Please enter a positive number."
            );
        }
    }

    private static String readNonEmpty() {

        while (true) {

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.print(
                    "Input cannot be empty. Enter again: "
            );
        }
    }
}