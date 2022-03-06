package service.impl;

import controllers.FuramaController;
import models.agreement.Booking;
import models.agreement.CustomerByYear;
import models.agreement.Voucher;
import models.facility.Facility;
import models.facility.House;
import models.facility.Room;
import models.facility.Villa;
import models.person.Customer;
import untils.SortByTimeBooking;

import java.io.*;
import java.util.*;

public class PromotionServiceImpl {
    List<Booking> bookingList = new ArrayList<>();
    List<Booking> bookingListSortedByTime = new ArrayList<>();
    List<Facility> facilityList = new ArrayList<>();
    List<CustomerByYear> customerByYearList = new ArrayList<>();
    List<Customer> customerList = new ArrayList<>();

    public PromotionServiceImpl() {
        readBookingFromFile();
        readFacility();
        readCustomer();

    }

    public void readFacility() {
        facilityList.clear();
        readHouse();
        readRoom();
        readVilla();
    }

    void readCustomer() {
        customerList.clear();
        File readCustomerProfile = new File("D:\\Test\\src\\data\\customers.csv");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readBookingFromFile() {
        bookingList.clear();
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

                    bookingList.add(booking);
                    bookingListSortedByTime.add(booking);
                } else {
                    Booking booking = new Booking(bookingArr[0], bookingArr[1], bookingArr[2],
                            bookingArr[3], bookingArr[4], bookingArr[5]);
                    booking.setQuantityOfBooking(Integer.parseInt(bookingArr[6]));
                    bookingList.add(booking);
                    bookingListSortedByTime.add(booking);
                }


            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addCustomersUseByYear() {
        String name;
        String bookingId;
        String customerId;
        String bookingTime;
        for (Booking booking : bookingList) {
            if (booking.getServiceType().length() > 1) {
                if (isYearType(booking.getServiceName(), booking.getServiceType())) {
                    name = getNameCustomer(booking.getIdCustomer());
                    customerId = booking.getIdCustomer();
                    bookingId = booking.getBookingId();
                    bookingTime = "start: " + booking.getStartDate() + " - end: " + booking.getEndDate();
                    CustomerByYear customerByYear = new CustomerByYear(name, bookingId, customerId, bookingTime);
                    customerByYearList.add(customerByYear);
                }
            } else {
                for (Facility facility : facilityList) {
                    if (facility.getServiceName().toLowerCase().equals(booking.getServiceName().toLowerCase())) {
                        if (facility.getRentType().toLowerCase().equals("year")) {
                            name = getNameCustomer(booking.getIdCustomer());
                            customerId = booking.getIdCustomer();
                            bookingId = booking.getBookingId();
                            bookingTime = "start: " + booking.getStartDate() + " - end: " + booking.getEndDate();
                            CustomerByYear customerByYear = new CustomerByYear(name, bookingId, customerId, bookingTime);
                            customerByYearList.add(customerByYear);
                            break;
                        }
                    }
                }
            }

        }
    }

    boolean isYearType(String serviceName, String serviceType) {
        for (Facility facility : facilityList) {
            if (facility instanceof Villa) {
                Villa villa = (Villa) facility;
                if (villa.getServiceName().toLowerCase().equals(serviceName.toLowerCase()) &&
                        villa.getRoomStandard().toLowerCase().equals(serviceType.toLowerCase())) {
                    if (villa.getRentType().toLowerCase().equals("year")) {
                        return true;
                    }
                }
            } else if (facility instanceof House) {
                House house = (House) facility;
                if (house.getServiceName().toLowerCase().equals(serviceName.toLowerCase()) &&
                        house.getRoomStandard().toLowerCase().equals(serviceType.toLowerCase())) {
                    if (house.getRentType().toLowerCase().equals("year")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    String getNameCustomer(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerId)) {
                return customer.getName();
            }
        }
        return "";
    }

    public void displayCustomerByYear() {
        addCustomersUseByYear();
        if (customerByYearList.size() > 0) {


            System.out.println();
            System.out.println(FuramaController.THUT + "-----------------------------------");
            System.out.println(FuramaController.THUT + "List Customer using service by year");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-60s%-12s%-12s%-15s%-35s%-12s", "", "|    Name",
                    "| Booking id", "|  customer Id", "|          Booking Time", "|");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------");
            for (CustomerByYear customerByYear : customerByYearList) {
                //(String name, String bookingId, String customerId, String bookingTime)
                System.out.printf("%-60s%-12s%-12s%-15s%-35s%-12s", "",
                        "| " + customerByYear.getName(), "| " + customerByYear.getBookingId(), "| " + customerByYear.getCustomerId(),
                        "| " + customerByYear.getBookingTime(), "|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------");
            }

            System.out.println();

        } else {
            System.out.println(FuramaController.THUT + "(^_^)_______________________________________________(^_^)");
            System.out.println(FuramaController.THUT + "There is not maintenance service. you can add every thing");
            System.out.println(FuramaController.THUT + "(^_^)_______________________________________________(^_^)");
        }

    }

    public void displayCustomersGetVoucher() {
        Scanner scanner = new Scanner(System.in);
        bookingListSortedByTime.sort(new SortByTimeBooking());
        Stack<String> voucherStack = new Stack<>();
        List<Voucher> voucherList = new ArrayList<>();
        int quantityOfCustomers = bookingListSortedByTime.size();
        if (quantityOfCustomers > 0) {
            int voucher10 = 0;
            int voucher20 = 0;
            int voucher30 = 0;
            int voucher40 = 0;
            int voucher50 = 0;
            System.out.println(FuramaController.THUT + "now quantity of customers is :" + quantityOfCustomers);
            count:
            while (true) {
                do {
                    System.out.println(FuramaController.THUT + "please input number of voucher 10%");
                    try {
                        voucher10 = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println(FuramaController.THUT + "please input a number");
                        continue;
                    }
                    if (voucher10 < 0 || voucher10 > quantityOfCustomers) {
                        System.out.println(FuramaController.THUT + "quantity of voucher 10 must bigger than 0 and less than quantity of customer");
                        continue;
                    } else {
                        for (int i = 0; i < voucher10; i++) {
                            voucherStack.push("10%");
                        }
                        if (voucher10 == quantityOfCustomers) {
                            break count;
                        }
                    }
                    break;
                } while (true);

                do {
                    System.out.println(FuramaController.THUT + "please input number of voucher 20%");
                    try {
                        voucher20 = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println(FuramaController.THUT + "please input a number");
                        continue;
                    }
                    if (voucher20 < 0 || voucher20 > quantityOfCustomers - voucher10) {
                        System.out.println(FuramaController.THUT + "quantity of voucher 20 must bigger than 0 and less than " + (quantityOfCustomers - voucher10));
                        continue;
                    } else {
                        for (int i = 0; i < voucher20; i++) {
                            voucherStack.push("20%");
                        }
                        if ((voucher10 + voucher20) == quantityOfCustomers) {
                            break count;
                        }
                    }
                    break;
                } while (true);

                do {
                    System.out.println(FuramaController.THUT + "please input number of voucher 30%");
                    try {
                        voucher30 = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println(FuramaController.THUT + "please input a number");
                        continue;
                    }
                    if (voucher30 < 0 || voucher30 > (quantityOfCustomers - voucher10 - voucher20)) {
                        System.out.println(FuramaController.THUT + "quantity of voucher 30 must bigger than 0 and less than " + (quantityOfCustomers - voucher10 - voucher20));
                        continue;
                    } else {
                        for (int i = 0; i < voucher30; i++) {
                            voucherStack.push("30%");
                        }
                        if ((voucher10 + voucher20 + voucher30) == quantityOfCustomers) {
                            break count;
                        }
                    }
                    break;
                } while (true);

                do {
                    System.out.println(FuramaController.THUT + "please input number of voucher 40%");
                    try {
                        voucher40 = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println(FuramaController.THUT + "please input a number");
                        continue;
                    }
                    if (voucher40 < 0 || voucher40 > (quantityOfCustomers - voucher10 - voucher20 - voucher30)) {
                        System.out.println(FuramaController.THUT + "quantity of voucher 40 must bigger than 0 and less than " + (quantityOfCustomers - voucher10 - voucher20 - voucher30));
                        continue;
                    } else {
                        for (int i = 0; i < voucher40; i++) {
                            voucherStack.push("40%");
                        }
                        if ((voucher10 + voucher20 + voucher30 + voucher40) == quantityOfCustomers) {
                            break count;
                        } else {
                            voucher50 = quantityOfCustomers - voucher10 - voucher20 - voucher30 - voucher40;
                            for (int i = 0; i < voucher50; i++) {
                                voucherStack.push("50%");
                            }
                        }
                    }
                    break;
                } while (true);
                break;
            }
            for (Booking booking : bookingListSortedByTime) {
                String customerId = booking.getIdCustomer();
                String name = getNameCustomer(booking.getIdCustomer());
                String value = voucherStack.pop();
                Voucher voucher = new Voucher(name, customerId, value);
                voucherList.add(voucher);
            }




            System.out.println();
            System.out.println(FuramaController.THUT + "---------------------------------");
            System.out.println(FuramaController.THUT + "List voucher fof customers booked");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------------------");
            System.out.println();
            System.out.printf("%-76s%-12s%-15s%-12s%-12s", "", "|    Name",
                    "| Customer id", "|  voucher", "|");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------------------");
            for (Voucher voucher : voucherList) {
                //(String name, String bookingId, String customerId, String bookingTime)
                System.out.printf("%-76s%-12s%-15s%-12s%-12s", "",
                        "| " + voucher.getName(), "| " + voucher.getCustomerId(), "| " + voucher.getVoucher(), "|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------------------");
            }

            System.out.println();
        } else {
            System.out.println(FuramaController.THUT + "(^_^)_______________________________________________(^_^)");
            System.out.println(FuramaController.THUT + "There is not customers now. please add");
            System.out.println(FuramaController.THUT + "(^_^)_______________________________________________(^_^)");
            System.out.println();
        }

    }
}

