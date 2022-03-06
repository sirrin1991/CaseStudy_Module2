package models.agreement;

public class CustomerByYear {
    private String name;
    private String bookingId;
    private String customerId;
    private String bookingTime;

    public CustomerByYear() {

    }

    public CustomerByYear(String name, String bookingId, String customerId, String bookingTime) {
        this.name = name;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.bookingTime = bookingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "CustomerByYear{" +
                "name='" + name + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                '}';
    }
}
