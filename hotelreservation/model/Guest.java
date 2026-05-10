package hotelreservation.model;

public class Guest {
    private String guestId;
    private String name;
    private String email;
    private String phoneNumber;

    public Guest(String guestId, String name, String email, String phoneNumber) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter
    public String getGuestId() { return guestId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}