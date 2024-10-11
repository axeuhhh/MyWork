package dig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DigAnywhere {
    public static void main(String[] args) {
        // Create a user database
        UserDatabase userDatabase = new UserDatabase();

        // Create a new user
        User user1 = new User("john_doe", "password123");
        userDatabase.addUser(user1);

        // Create a request for the user
        String projectDescription = "Build a commercial building.";
        String response = "You will need 5 cranes and 10 bulldozers.";
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        UserRequest userRequest = new UserRequest(projectDescription, response, dateTime);

        // Add request to the user
        userDatabase.addRequestToUser("john_doe", userRequest);

        // Display all users and their requests
        userDatabase.displayAllUsers();
    }
}
