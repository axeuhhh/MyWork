package dig;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<UserRequest> userRequests;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userRequests = new ArrayList<>();
    }

    // Add a new request to the user's list of requests
    public void addRequest(UserRequest request) {
        userRequests.add(request);
    }

    // Get all requests for the user
    public List<UserRequest> getUserRequests() {
        return userRequests;
    }

    // Get user information
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Overriding toString() for easy user info display
    @Override
    public String toString() {
        return "Username: " + username + " | Number of requests: " + userRequests.size();
    }
}