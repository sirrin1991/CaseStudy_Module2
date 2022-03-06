package service.impl;

import controllers.FuramaController;
import models.agreement.Booking;
import models.facility.Facility;
import models.facility.House;
import models.facility.Room;
import models.facility.Villa;
import models.person.Customer;
import service.IBookingContract;
import untils.SortByStartDay;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class BookingServiceImpl implements IBookingContract {
    static String PATH_CUSTOMER = "D:\\Test\\src\\data\\customers.csv";
    static List<Customer> customerList = new ArrayList<>();
    static List<Facility> facilityList = new ArrayList<>();
    static Set<Booking> bookingSet = new TreeSet<>();
    static List<Booking> bookingList = new ArrayList<>();
    static int count = 0;

    public BookingServiceImpl() {
        readCustomer();
        facilityList.clear();
        readHouse();
        readRoom();
        readVilla();
        setQuantityOfBooking();
        read();
        write();
        setBookingOfFacility();
        writeMaintain();
    }

    static void readCustomer() {
        customerList.clear();
        File readCustomerProfile = new File(PATH_CUSTOMER);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(readCustomerProfile);
            BufferedReader br = new BufferedReader(fileReader);
            String data = null;
            while ((data = br.readLine()) != null) {
                String[] dataA = data.split(",");
                Customer customer = new Customer(dataA[0], dataA[1], dataA[2], dataA[3],
                        dataA[4], dataA[5], dataA[6],
                        dataA[7], dataA[8]);
                customerList.add(customer);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void readRoom() {
        final String PATH_ROOM = "D:\\Test\\src\\data\\room.csv";
        try {
            FileReader fileReader = new FileReader(PATH_ROOM);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                String[] roomArray = data.split(",");
                Room room = new Room(roomArray[0], roomArray[1], Double.parseDouble(roomArray[2]), Integer.parseInt(roomArray[3]),
                        Integer.parseInt(roomArray[4]), roomArray[5], roomArray[6]);
                facilityList.add(room);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void readHouse() {
        final String PATH_HOUSE = "D:\\Test\\src\\data\\house.csv";
        try {
            FileReader fileReader = new FileReader(PATH_HOUSE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                String[] houseArray = data.split(",");
                House house = new House(houseArray[0], houseArray[1], Double.parseDouble(houseArray[2]), Integer.parseInt(houseArray[3]),
                        Integer.parseInt(houseArray[4]), houseArray[5], houseArray[6],
                        Integer.parseInt(houseArray[7]));
                facilityList.add(house);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readVilla() {
        final String PATH_VILLA = "D:\\Test\\src\\data\\villa.csv";
        try {
            FileReader fileReader = new FileReader(PATH_VILLA);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                String[] villaArray = data.split(",");
                Villa villa = new Villa(villaArray[0], villaArray[1], Double.parseDouble(villaArray[2]), Integer.parseInt(villaArray[3]),
                        Integer.parseInt(villaArray[4]), villaArray[5], villaArray[6],
                        Double.parseDouble(villaArray[7]), Integer.parseInt(villaArray[8]));
                facilityList.add(villa);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateQuantityOfBookingForFacility() {

    }

    static void setBookingOfFacility() {
        for (Booking booking : bookingSet) {
            for (Facility facility : facilityList) {
                if (facility instanceof Villa) {
                    Villa villa = (Villa) facility;
                    if (villa.getServiceName().toLowerCase().equals(booking.getServiceName().toLowerCase()) &&
                            villa.getRoomStandard().toLowerCase().equals(booking.getServiceType().toLowerCase())) {
                        facility.setQuantityOfBooking(booking.getQuantityOfBooking());
                    }

                } else if (facility instanceof House) {
                    House house = (House) facility;
                    if (house.getServiceName().toLowerCase().equals(booking.getServiceName().toLowerCase()) &&
                            house.getRoomStandard().toLowerCase().equals(booking.getServiceType().toLowerCase())) {
                        facility.setQuantityOfBooking(booking.getQuantityOfBooking());
                    }
                } else {
                    if (facility.getServiceName().toLowerCase().equals(booking.getServiceName().toLowerCase())) {
                        facility.setQuantityOfBooking(booking.getQuantityOfBooking());
                    }
                }

            }
        }
    }


    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        boolean check;
        String bookingId = bookingIdAuto();
        String customerId = null;
        String endDate;
        String startDate;
        String serviceName = null;
        String serviceType = "";
        String rentType = null;
        List<Facility> listServiceType;
        String now = LocalDate.now() + "";
        String[] nowArr = now.split("-");
        long nowToMillisecond = new Date(Integer.parseInt(nowArr[0]), Integer.parseInt(nowArr[1]), Integer.parseInt(nowArr[2])).getTime();

        if (customerList.size() > 0) {
            if (facilityList.size() > 0) {
                displayCustomers();
                do {
                    check = true;
                    System.out.println(FuramaController.THUT + "please input id of customer");
                    customerId = scanner.nextLine();
                    for (Customer customer : customerList) {
                        if (customerId.toLowerCase().equals(customer.getId().toLowerCase())) {
                            customerId = customer.getId();
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        System.out.println(FuramaController.THUT + "Id does not exist,please input again");
                    }

                } while (check);


                System.out.println();
                displayFacility();
                do {
                    check = true;
                    System.out.println(FuramaController.THUT + "Please input service name what you want to book");
                    serviceName = scanner.nextLine();
                    for (Facility facility : facilityList) {
                        if (serviceName.toLowerCase().equals(facility.getServiceName().toLowerCase())) {
                            serviceName = facility.getServiceName();
                            check = false;
                            if (facility instanceof Villa) {
                                Villa villa = (Villa) facility;
                                serviceType = villa.getRoomStandard();
                                break;
                            } else if (facility instanceof House) {
                                House house = (House) facility;
                                serviceType = house.getRoomStandard();
                                break;
                            }
                        }
                    }
                    if (check) {
                        System.out.println(FuramaController.THUT + "service does not exist,please input again");
                    }
                } while (check);


                do {
                    check = true;
                    listServiceType = new ArrayList<>();
                    if (!serviceName.toLowerCase().contains("room")) {
                        for (Facility facility : facilityList) {
                            if (facility.getServiceName().toLowerCase().equals(serviceName.toLowerCase())) {
                                if (facility instanceof House) {
                                    House house = (House) facility;
                                    serviceType = house.getRoomStandard();
                                    rentType = house.getRentType();
                                } else {
                                    Villa villa = (Villa) facility;
                                    serviceType = villa.getRoomStandard();
                                    rentType = villa.getRentType();
                                }
                                listServiceType.add(facility);
                            }
                        }
                        check = false;
                        if (listServiceType.size() > 1) {
                            for (Facility facility : listServiceType) {
                                System.out.println(facility);
                            }

                            do {
                                check = true;
                                System.out.println(FuramaController.THUT + "please input service type");
                                serviceType = scanner.nextLine();
                                if (serviceName.toLowerCase().contains("villa")) {
                                    for (Facility facility : listServiceType) {
                                        Villa villa = (Villa) facility;
                                        if (villa.getRoomStandard().toLowerCase().equals(serviceType.toLowerCase())) {
                                            serviceType = villa.getRoomStandard();
                                            rentType = villa.getRentType();
                                            check = false;
                                            break;
                                        }
                                    }
                                } else {
                                    for (Facility facility : listServiceType) {
                                        House house = (House) facility;
                                        if (house.getRoomStandard().toLowerCase().equals(serviceType.toLowerCase())) {
                                            serviceType = house.getRoomStandard();
                                            rentType = house.getRentType();
                                            check = false;
                                            break;
                                        }
                                    }
                                }

                                if (check) {
                                    System.out.println(FuramaController.THUT + "rent type does not exist,please input again");
                                }
                            } while (check);


                        }
                    } else {
                        check = true;
                        for (Facility facility : facilityList) {
                            if (facility.getServiceName().toLowerCase().equals(serviceName.toLowerCase())) {
                                Room room = (Room) facility;
                                rentType = room.getRentType();
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            System.out.println(FuramaController.THUT + "rent type does not exist,please input again");
                        }
                    }

                } while (check);

            } else {
                System.out.println();
                System.out.println(FuramaController.THUT + "(^_^_________________________(^_^)");
                System.out.println(FuramaController.THUT + "not exist data,please add new data");
                System.out.println(FuramaController.THUT + "(^_^_________________________(^_^)");
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println(FuramaController.THUT + "(^_^_________________________(^_^)");
            System.out.println(FuramaController.THUT + "not exist data,please add new data");
            System.out.println(FuramaController.THUT + "(^_^_________________________(^_^)");
            System.out.println();
        }


        do {
            System.out.println(FuramaController.THUT + "please input start day(dd/mm/yy)");
            startDate = scanner.nextLine();
            if (checkDate(startDate)) {
                String[] startDateArr = startDate.split("/");
                long starDayToMilliseconds = new Date(Integer.parseInt(startDateArr[2]), Integer.parseInt(startDateArr[1]), Integer.parseInt(startDateArr[0])).getTime();
                if (starDayToMilliseconds - nowToMillisecond < 0) {
                    System.out.println(FuramaController.THUT + "this time is less than current time,please input again");
                } else break;
            }
        } while (true);


        do {
            System.out.println(FuramaController.THUT + "please input end day(dd/mm/yy)");
            endDate = scanner.nextLine();
            if (checkDate(endDate)) {
                int forHour = 1;
                int forDay = 30;
                int forMonth = 30 * 12;
                String[] endDayArr = endDate.split("/");
                String[] startDateArr = startDate.split("/");
                long endDayToMilliseconds = new Date(Integer.parseInt(endDayArr[2]), Integer.parseInt(endDayArr[1]), Integer.parseInt(endDayArr[0])).getTime();
                long starDayToMilliseconds = new Date(Integer.parseInt(startDateArr[2]), Integer.parseInt(startDateArr[1]), Integer.parseInt(startDateArr[0])).getTime();
                if (endDayToMilliseconds - starDayToMilliseconds < 0) {
                    System.out.println(FuramaController.THUT + "end time is less than start time,please input again");
                    continue;
                } else {
                    LocalDate startLocalDate = LocalDate.of(Integer.parseInt(endDayArr[2]), Integer.parseInt(endDayArr[1]), Integer.parseInt(endDayArr[0]));
                    LocalDate endLocalDate = LocalDate.of(Integer.parseInt(startDateArr[2]), Integer.parseInt(startDateArr[1]), Integer.parseInt(startDateArr[0]));
                    int days = Period.between(endLocalDate, startLocalDate).getDays();
                    int month = Period.between(endLocalDate, startLocalDate).getMonths();
                    int year = Period.between(endLocalDate, startLocalDate).getYears();
                    if ("year".toLowerCase().equals(rentType)) {
                        if (year < 1) {
                            System.out.println(FuramaController.THUT + "you have selected the service for year");
                            System.out.println(FuramaController.THUT + "please select another services or pick another period");
                            continue;
                        }
                    } else if ("month".toLowerCase().equals(rentType)) {
                        if ((month < 0 && year < 1) || year > 1) {
                            System.out.println(FuramaController.THUT + "you have selected the service for month");
                            System.out.println(FuramaController.THUT + "please select another services or pick another period");
                            continue;
                        }
                    } else if ("day".toLowerCase().equals(rentType)) {
                        if ((days > 0 && month > 0 && year > 0) || (days < 0 && month < 0 && year < 0)) {
                            System.out.println(FuramaController.THUT + "you have selected the service for day");
                            System.out.println(FuramaController.THUT + "please select another services or pick another period");
                            continue;
                        }
                    } else {
                        if ((days > 0 || month > 0 || year > 0)) {
                            System.out.println(FuramaController.THUT + "you have selected the service for hour");
                            System.out.println(FuramaController.THUT + "please select another services or pick another period");
                            continue;
                        }
                    }
                }
                break;
            }
        } while (true);

        if (!"".equals(serviceType)) {
            Booking booking = new Booking(bookingId, startDate, endDate, customerId, serviceName, serviceType, now + "/" + System.currentTimeMillis());
            if (updateQuantityOfBooking(booking.getServiceName(), booking.getServiceType(), booking)) {
                check = true;
                for (Booking booking1 : bookingSet) {
                    if (booking1.equals(booking)) {
                        booking1.setQuantityOfBooking(booking.getQuantityOfBooking());
                        check = false;
                        break;
                    }
                }

                if (check) {
                    bookingSet.add(booking);
                    bookingList.add(booking);
                }

                writeMaintain();
                write();
            } else System.out.println(FuramaController.THUT + "this service has maintained,please select other");
        } else {
            Booking booking = new Booking(bookingId, startDate, endDate, customerId, serviceName, now + "/" + System.currentTimeMillis());

            if (updateQuantityOfBooking(booking.getServiceName(), "", booking)) {
                check = true;

                for (Booking booking1 : bookingSet) {
                    if (booking1.equals(booking)) {
                        booking1.setQuantityOfBooking(booking.getQuantityOfBooking());
                        check = false;
                        break;
                    }
                }
                if (check) {
                    bookingSet.add(booking);
                    bookingList.add(booking);
                }

                writeMaintain();
                write();

            } else System.out.println(FuramaController.THUT + "this service has maintained,please select other");

        }

    }

    @Override
    public void display() {

        if (bookingList.size() > 0) {
            bookingList.sort(new SortByStartDay());
            System.out.println(FuramaController.THUT + "------------");
            System.out.println(FuramaController.THUT + "List Booking");
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t-----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-14s%-15s%-15s%-15s%-15s%-20s%-15s%-30s%-20s%-14s", "", "|   bookingId",
                    "|   start day", "|    end day", "|  customer id", "|   service name",
                    "| service type", "|       booking time", "|  booking quantity", "|");
            System.out.println();
            System.out.println("\t\t\t\t-------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Booking booking : bookingList) {
                System.out.printf("%-14s%-15s%-15s%-15s%-15s%-20s%-15s%-30s%-20s%-14s", "",
                        "| " + booking.getBookingId(), "| " + booking.getStartDate(), "| " + booking.getEndDate(), "| " + booking.getIdCustomer(), "| " + booking.getServiceName(),
                        "| " + booking.getServiceType(), "| " + booking.getBookingTime(), "| " + booking.getQuantityOfBooking(), "|");
                System.out.println();
                System.out.println("\t\t\t\t------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            System.out.println();
        } else {
            System.out.println();
            System.out.println(FuramaController.THUT + "(^_^)_________(^_^)");
            System.out.println(FuramaController.THUT + "no data,please book");
            System.out.println(FuramaController.THUT + "(^_^)_________(^_^)");
            System.out.println();
        }

    }

    void displayCustomers() {
        System.out.println();
        System.out.println(FuramaController.THUT + "List Customers");
        System.out.println();
        System.out.println(FuramaController.HALF_THUT + "-------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.printf("%-28s%-12s%-12s%-15s%-12s%-12s%-12s%-18s%-12s%-15s%-12s", "", "|     Id",
                "|    name", "|   birth day", "|   gender", "| identity",
                "|   phone", "|     email", "| type", "| address", "|");
        System.out.println();
        System.out.println(FuramaController.HALF_THUT + "-------------------------------------------------------------------------------------------------------------------------");
        for (Customer customer : customerList) {
            System.out.printf("%-28s%-12s%-12s%-15s%-12s%-12s%-12s%-18s%-12s%-15s%-12s", " ",
                    "| " + customer.getId(), "| " + customer.getName(), "| " + customer.getDayOfBirth(), "| " + customer.getGender(), "| " + customer.getIdentityCardNumber(),
                    "| " + customer.getNumberPhone(), "| " + customer.getEmail(), "| " + customer.getCustomerType(), "| " + customer.getAddress(), "|");
            System.out.println();
            System.out.println(FuramaController.HALF_THUT + "-------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println();

    }

    void displayFacility() {
        boolean checkVilla = false;
        boolean checkRoom = false;
        boolean checkHouse = false;
        if (facilityList.size() > 0) {
            for (Facility facility : facilityList) {
                if (facility instanceof Villa) {
                    checkVilla = true;
                }
                if (facility instanceof House) {
                    checkHouse = true;
                }
                if (facility instanceof Room) {
                    checkRoom = true;
                }
            }

            if (checkVilla) {
                System.out.println(FuramaController.THUT + "----------");
                System.out.println(FuramaController.THUT + "List Villa");
                System.out.println();
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-35s%-12s%-12s%-15s%-12s%-12s%-12s%-14s%-12s%-12s%-12s", "", "|    Id",
                        "|  service", "|  area usable", "|    rent", "|  people",
                        "| rent type", "|  room stand", "| pool area", "|   floor", "|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------------------------------------------------------");
                for (Facility facility : facilityList) {
                    if (facility instanceof Villa) {
                        Villa villa = (Villa) facility;
                        System.out.printf("%-35s%-12s%-12s%-15s%-12s%-12s%-12s%-14s%-12s%-12s%-12s", "",
                                "| " + villa.getCodeService(), "| " + villa.getServiceName(), "| " + villa.getAreaUsable(), "| " + villa.getRent(), "| " + villa.getMaxPeople(),
                                "| " + villa.getRentType(), "| " + villa.getRoomStandard(), "| " + villa.getPoolArea(), "| " + villa.getQuantityOfFloor(), "|");
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t\t\t----------------------------------------------------------------------------------------------------------------");
                    }

                }
            }

            if (checkHouse) {

                System.out.println(FuramaController.THUT + "----------");

                System.out.println(FuramaController.THUT + "List House");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-39s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-12s%-12s", "", "|     Id",
                        "|  service", "|  area usable", "|  rent", "|   people",
                        "|rent type", "|room stand", "|   floor", "|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                for (Facility facility : facilityList) {
                    if (facility instanceof House) {
                        House house = (House) facility;
                        System.out.printf("%-39s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-12s%-12s", "",
                                "| " + house.getCodeService(), "| " + house.getServiceName(), "| " + house.getAreaUsable(), "| " + house.getRent(), "| " + house.getMaxPeople(),
                                "| " + house.getRentType(), "| " + house.getRoomStandard(), "| " + house.getQuantityOfFloor(), "|");
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                    }

                }
            }


            if (checkRoom) {
                System.out.println();
                System.out.println(FuramaController.THUT + "---------");
                System.out.println(FuramaController.THUT + "List Room");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-43s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-35s", "", "|    Id",
                        "| service", "|  area usable", "|   rent", "|   people",
                        "| rent type", "| free ", "|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                for (Facility facility : facilityList) {
                    if (facility instanceof Room) {
                        Room room = (Room) facility;
                        System.out.printf("%-43s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-35s", "",
                                "| " + room.getCodeService(), "| " + room.getServiceName(), "| " + room.getAreaUsable(), "| " + room.getRent(),
                                "| " + room.getMaxPeople(), "| " + room.getRentType(), "| " + room.getFreeService(), "|");
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                    }

                }
                System.out.println();
            }
        }
    }

    int random() {
        return (int) Math.floor(Math.random() * 10);
    }

    String bookingIdAuto() {
        return "BOOKING" + "" + random() + "" + random() + random();
    }


    public List<Facility> displayMaintain() {
        List<Facility> listMaintain = new ArrayList<>();
        for (Facility facility : facilityList) {
            if (facility.getQuantityOfBooking() > 4) {
                listMaintain.add(facility);
            }
        }
        return listMaintain;
    }

    boolean updateQuantityOfBooking(String nameService, String stand, Booking booking) {
        if ("".equals(stand)) {

            for (Facility facility : facilityList) {

                if (facility.getServiceName().toLowerCase().equals(nameService.toLowerCase())) {
                    if (facility.getQuantityOfBooking() < 5) {
                        facility.setQuantityOfBooking(facility.getQuantityOfBooking() + 1);
                        booking.setQuantityOfBooking(facility.getQuantityOfBooking());
                        return true;
                    }

                }

            }

        } else {
            for (Facility facility : facilityList) {
                if (facility instanceof Villa) {
                    Villa villa = (Villa) facility;
                    if (villa.getServiceName().toLowerCase().equals(nameService.toLowerCase()) &&
                            villa.getRoomStandard().toLowerCase().equals(stand.toLowerCase())) {
                        if (facility.getQuantityOfBooking() < 5) {
                            facility.setQuantityOfBooking(facility.getQuantityOfBooking() + 1);
                            booking.setQuantityOfBooking(facility.getQuantityOfBooking());
                            return true;
                        }

                    }
                } else if (facility instanceof House) {
                    House house = (House) facility;
                    if (house.getServiceName().toLowerCase().equals(nameService.toLowerCase()) &&
                            house.getRoomStandard().toLowerCase().equals(stand.toLowerCase())) {
                        if (facility.getQuantityOfBooking() < 5) {
                            facility.setQuantityOfBooking(facility.getQuantityOfBooking() + 1);
                            booking.setQuantityOfBooking(facility.getQuantityOfBooking());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    boolean checkDate(String date) {
        String REGEX_BIRTHDAY = "[0-9]{1,2}/[0-9]{1,2}/20[2-9][0-9]";
        if (date.matches(REGEX_BIRTHDAY)) {
            String[] dayOfBirthArray = date.split("/");
            if ((Integer.parseInt(dayOfBirthArray[1])) < 0 || (Integer.parseInt(dayOfBirthArray[1])) > 12) {
                System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                return false;
            } else {
                switch (Integer.parseInt(dayOfBirthArray[1])) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (Integer.parseInt(dayOfBirthArray[0]) < 0 || (Integer.parseInt(dayOfBirthArray[0]) > 31)) {
                            System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                            return false;
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (Integer.parseInt(dayOfBirthArray[0]) < 0 || (Integer.parseInt(dayOfBirthArray[0]) > 30)) {
                            System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                            return false;
                        }
                        break;
                    case 2:
                        if ((Integer.parseInt(dayOfBirthArray[2]) % 4 == 0 && Integer.parseInt(dayOfBirthArray[2]) % 100 != 0) || Integer.parseInt(dayOfBirthArray[2]) % 400 == 0) {
                            if (Integer.parseInt(dayOfBirthArray[0]) < 0 || Integer.parseInt(dayOfBirthArray[0]) > 29) {
                                System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                                return false;
                            }
                        } else {
                            if (Integer.parseInt(dayOfBirthArray[0]) < 0 || Integer.parseInt(dayOfBirthArray[0]) > 28) {
                                System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                                return false;
                            }
                        }
                }

                return true;
            }
        }
        return false;
    }


    static void write() {
        File file = new File("D:\\Test\\src\\data\\booking.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Booking booking : bookingSet) {

                bufferedWriter.write(booking.getBookingId() + "," + booking.getStartDate() + "," + booking.getEndDate() + "," +
                        booking.getIdCustomer() + "," + booking.getServiceName() + "," + booking.getServiceType() + "," + booking.getBookingTime() + "," + booking.getQuantityOfBooking());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void read() {
        bookingList.clear();
        bookingSet.clear();
        File file = new File("D:\\Test\\src\\data\\booking.csv");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                String[] bookingArr = data.split(",");
                if (bookingArr.length == 8) {
                    Booking booking = new Booking(bookingArr[0], bookingArr[1], bookingArr[2],
                            bookingArr[3], bookingArr[4], bookingArr[5], bookingArr[6]);
                    booking.setQuantityOfBooking(Integer.parseInt(bookingArr[7]));
                    bookingSet.add(booking);
                    bookingList.add(booking);
                } else {
                    Booking booking = new Booking(bookingArr[0], bookingArr[1], bookingArr[2],
                            bookingArr[3], bookingArr[4], bookingArr[5]);
                    booking.setQuantityOfBooking(Integer.parseInt(bookingArr[6]));
                    bookingSet.add(booking);
                    bookingList.add(booking);
                }


            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    static void writeMaintain() {
        String PATH = "D:\\Test\\src\\data\\maintain.csv";
        try {

            FileWriter fileWriter = new FileWriter(PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = null;
            for (Facility facility : facilityList) {

                if (facility.getQuantityOfBooking() >= 5) {
                    if (facility instanceof Villa) {
                        Villa villa = (Villa) facility;
                        bufferedWriter.write(villa.getCodeService() + "," + villa.getServiceName() + "," + villa.getAreaUsable() + "," +
                                villa.getRent() + "," + villa.getMaxPeople() + "," + villa.getRentType() + "," +
                                villa.getRoomStandard() + "," + villa.getPoolArea() + "," + villa.getQuantityOfFloor());
                        bufferedWriter.newLine();
                    } else if (facility instanceof House) {
                        House house = (House) facility;
                        bufferedWriter.write(house.getCodeService() + "," + house.getServiceName() + "," + house.getAreaUsable() + "," +
                                house.getRent() + "," + house.getMaxPeople() + "," + house.getRent() + "," +
                                house.getRoomStandard() + "," + house.getQuantityOfFloor());
                        bufferedWriter.newLine();
                    } else {
                        Room room = (Room) facility;
                        bufferedWriter.write(room.getCodeService() + "," + room.getServiceName() + "," + room.getAreaUsable() + "," +
                                room.getRent() + "," + room.getMaxPeople() + "," + room.getRentType() + "," + room.getFreeService());
                        bufferedWriter.newLine();
                    }


                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Facility> readMaintain() {

        List<Facility> facilityList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("D:\\Test\\src\\data\\maintain.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine()) != null) {
                String[] dataArr = data.split(",");
                if (dataArr[0].toLowerCase().contains("svvl")) {
                    
                    Villa villa = new Villa(dataArr[0], dataArr[1], Double.parseDouble(dataArr[2]), Integer.parseInt(dataArr[3]),
                            Integer.parseInt(dataArr[4]), dataArr[5], dataArr[6], Double.parseDouble(dataArr[7]), Integer.parseInt(dataArr[8]));
                    facilityList.add(villa);
                } else if (dataArr[0].toLowerCase().contains("svho")) {
                    House house = new House(dataArr[0], dataArr[1], Double.parseDouble(dataArr[2]),
                            Integer.parseInt(dataArr[3]), Integer.parseInt(dataArr[4]),
                            dataArr[5], dataArr[6], Integer.parseInt(dataArr[7]));
                    facilityList.add(house);
                } else {
                    Room room = new Room(dataArr[0], dataArr[1], Double.parseDouble(dataArr[2]),
                            Integer.parseInt(dataArr[3]), Integer.parseInt(dataArr[4]),
                            dataArr[5], dataArr[6]);
                    facilityList.add(room);
                }

            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return facilityList;
    }

    static void setQuantityOfBooking() {
        count++;
        LocalDate now = LocalDate.now();
        int currentDay = now.getDayOfMonth();
        if (currentDay == 1) {
            if (count == 1) {
                for (Facility facility : facilityList) {
                    facility.setQuantityOfBooking(0);
                }
            }

        }
    }
}
