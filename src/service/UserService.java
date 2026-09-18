package service;

import model.User;

import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User registered successfully!");
    }

    public void viewUsers() {

        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        System.out.println("\n===== REGISTERED USERS =====");

        for (User user : users) {
            System.out.println(user);
            System.out.println("----------------------");
        }
    }

    public User findUserById(int userId) {

        for (User user : users) {

            if (user.getUserId() == userId) {
                return user;
            }
        }

        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}