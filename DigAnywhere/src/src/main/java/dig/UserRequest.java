package dig;

public class UserRequest implements Comparable<UserRequest> {
    private String projectDescription;
    private String response;
    private String dateAndTime; // You can use LocalDateTime instead for better handling of dates

    // Constructor
    public UserRequest(String projectDescription, String response, String dateAndTime) {
        this.projectDescription = projectDescription;
        this.response = response;
        this.dateAndTime = dateAndTime;
    }

    // Getter methods
    public String getProjectDescription() {
        return projectDescription;
    }

    public String getResponse() {
        return response;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public int compareTo(UserRequest o) {
        return this.dateAndTime.compareTo(o.dateAndTime); // Sort based on date and time
    }

    @Override
    public String toString() {
        return "Request: " + projectDescription + " | Response: " + response + " | Date: " + dateAndTime;
    }
}
