package untils;

import models.agreement.Booking;

import java.util.Comparator;
import java.util.Date;

public class SortByStartDay implements Comparator<Booking> {
    @Override
    public int compare(Booking o1, Booking o2) {

        String[] o1EndArr = o1.getEndDate().split("/");
        String[] o2EndArr = o2.getEndDate().split("/");
        String[] o1StartArr = o1.getStartDate().split("/");
        String[] o2StartArr = o2.getStartDate().split("/");

        long o1DateStart = new Date(Integer.parseInt(o1StartArr[2]),
                Integer.parseInt(o1StartArr[1]), Integer.parseInt(o1StartArr[0])).getTime();
        long o2DateStart = new Date(Integer.parseInt(o2StartArr[2]),
                Integer.parseInt(o2StartArr[1]), Integer.parseInt(o2StartArr[0])).getTime();
        long o1DateEnd = new Date(Integer.parseInt(o1EndArr[2]),
                Integer.parseInt(o1EndArr[1]), Integer.parseInt(o1EndArr[0])).getTime();
        long o2DateEnd = new Date(Integer.parseInt(o2EndArr[2]),
                Integer.parseInt(o2EndArr[1]), Integer.parseInt(o2EndArr[0])).getTime();

        if (o1DateStart - o2DateStart > 0) {
            return 1;
        } else if (o1DateStart - o2DateStart < 0) {
            return -1;
        } else {
            if (o1DateEnd - o2DateEnd > 0) {
                return 1;
            } else if (o1DateEnd - o2DateEnd < 0) {
                return -1;
            } else return 0;
        }

    }
}
