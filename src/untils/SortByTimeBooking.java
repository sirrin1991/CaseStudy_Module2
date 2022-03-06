package untils;

import models.agreement.Booking;

import java.util.Comparator;

public class SortByTimeBooking implements Comparator<Booking> {
    @Override
    public int compare(Booking o1, Booking o2) {
        String o1Time = o1.getBookingTime().split("/")[1];
        String o2Time = o2.getBookingTime().split("/")[1];
        if (Long.parseLong(o1Time) - Long.parseLong(o2Time) > 0) {
            return 1;
        } else if (Long.parseLong(o1Time) - Long.parseLong(o2Time) < 0) {
            return -1;
        } else return 0;
    }
}
