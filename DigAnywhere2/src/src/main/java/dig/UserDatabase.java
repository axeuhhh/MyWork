package dig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDatabase {
    private Map<String, User> users; // Store users based on their username

    // Constructor
    public UserDatabase() {
        users = new HashMap<>();
    }

    // Add a new user to the database
    public void addUser(User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
        } else {
            System.out.println("User already exists.");
        }
    }

    // Get a user from the database based on username
    public User getUser(String username) {
        return users.get(username);
    }

    // Check if a user exists
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    // Add a request to a user
    public void addRequestToUser(String username, UserRequest request) {
        User user = users.get(username);
        if (user != null) {
            user.addRequest(request);
        } else {
            System.out.println("User not found.");
        }
    }

    // Get all users in the database
    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }

    // Print all users and their requests
    public void displayAllUsers() {
        for (User user : users.values()) {
            System.out.println(user);
            for (UserRequest request : user.getUserRequests()) {
                System.out.println("  " + request);
            }
        }
    }
}