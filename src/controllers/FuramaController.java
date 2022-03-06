package controllers;

import service.impl.*;

import java.util.Scanner;

public class FuramaController {
    public static final String THUT = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String HALF_THUT = "\t\t\t\t\t\t\t";
    static Scanner scanner= new Scanner(System.in);
    static int choice;
    static boolean checkChoice;
    public static void main(String[] args) {
        displayMainMenu();
    }

    static void displayMainMenu(){
        do{

            checkChoice = false;
            System.out.println();
            System.out.println();
            System.out.println(THUT + "(-_-) Furama resort management menu (-_-)");
            System.out.println(THUT + "1. Employee management");
            System.out.println(THUT + "2. Customer management");
            System.out.println(THUT + "3. Facility management");
            System.out.println(THUT + "4. Booking management");
            System.out.println(THUT + "5. Promotion management");
            System.out.println(THUT + "6 Exit");
            System.out.println();
            System.out.println(THUT + "(-_-)___________(-_-)");
            System.out.println(THUT + "Enter your selection");

            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println(THUT +"look at the list carefully");
                checkChoice = true;
                continue;
            }

            switch (choice){
                case 1:
                    employeeManagement();
                    break;
                case 2:
                    customerManagement();
                    break;
                case 3:
                    facilityManagement();
                    break;
                case 4:
                    bookingManagement();
                    break;
                case 5:
                    promotionManagement();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println(THUT +"look at the list carefully");
                    checkChoice = true;
                    continue;
            }
        }while(checkChoice);
    }

    static void employeeManagement(){
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        do {
            checkChoice = false;
            System.out.println(THUT +"1. Display list employees");
            System.out.println(THUT +"2. Add new employees");
            System.out.println(THUT +"3. Edit employees");
            System.out.println(THUT +"4. Return main menu");
            System.out.println();
            System.out.println(THUT +"(-_-)___________(-_-)");
            System.out.println(THUT +"Enter your selection");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(THUT +"look at the list carefully");
                checkChoice = true;
                continue;
            }
            switch (choice){
                case 1:
                    employeeService.display();
                    checkChoice = true;
                    continue;
                case 2:
                    employeeService.add();
                    checkChoice = true;
                    continue;
                case 3:
                    employeeService.edit();
                    checkChoice = true;
                    continue;
                case 4:
                    displayMainMenu();
                    break;
                default:
                    System.out.println(THUT +"look at the list carefully");
                    checkChoice = true;
                    continue;
            }
        } while (checkChoice);

    }

    static void customerManagement() {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        do {
            checkChoice = false;
            System.out.println(THUT +"1. Display list customers");
            System.out.println(THUT +"2. Add new customers");
            System.out.println(THUT +"3. Edit customers");
            System.out.println(THUT +"4. Return main menu");
            System.out.println();
            System.out.println(THUT +"(-_-)___________(-_-)");
            System.out.println(THUT +"Enter your selection");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(THUT +"look at the list carefully");
                checkChoice = true;
                continue;
            }
            switch (choice) {
                case 1:
                    customerService.display();
                    checkChoice = true;
                    continue;
                case 2:
                    customerService.add();
                    checkChoice = true;
                    continue;
                case 3:
                    customerService.edit();
                    checkChoice = true;
                    continue;
                case 4:
                    displayMainMenu();
                    break;
                default:
                    System.out.println(THUT +"look at the list carefully");
                    checkChoice = true;
                    continue;
            }
        } while (checkChoice);
    }

    static void facilityManagement(){
        FacilityServiceImpl facilityService = new FacilityServiceImpl();
        PromotionServiceImpl promotionService = new PromotionServiceImpl();
        do {
            checkChoice = false;
            System.out.println(THUT +"1. Display list facility");
            System.out.println(THUT +"2. Add new facility");
            System.out.println(THUT +"3. Display list facility maintenance");
            System.out.println(THUT +"4. Return main menu");
            System.out.println();
            System.out.println(THUT +"(-_-)___________(-_-)");
            System.out.println(THUT +"Enter your selection");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(THUT +"look at the list carefully");
                checkChoice = true;
                continue;
            }
            switch (choice){
                case 1:
                    facilityService.display();
                    checkChoice = true;
                    continue;
                case 2:
                    do{
                        checkChoice = false;
                        System.out.println(THUT +"1. Add new Villa");
                        System.out.println(THUT +"2. Add new House");
                        System.out.println(THUT +"3. Add new Room");
                        System.out.println(THUT +"4. Return main menu");
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println(THUT +"look at the list carefully");
                            checkChoice = true;
                            continue;
                        }
                        switch (choice){
                            case 1:
                                facilityService.add(1);
                                promotionService.readFacility();
                                checkChoice = true;
                                continue;
                            case 2:
                                facilityService.add(2);
                                promotionService.readFacility();
                                checkChoice = true;
                                continue;
                            case 3:
                                facilityService.add(3);
                                promotionService.readFacility();
                                checkChoice = true;
                                continue;
                            case 4:
                                facilityManagement();
                                checkChoice = true;
                                continue;
                            default:
                                System.out.println(THUT +"look at the list carefully");
                                checkChoice = true;
                                continue;

                        }

                    }while(checkChoice);
                case 3:
                    facilityService.displayListMaintenance();
                    checkChoice = true;
                    continue;
                case 4:
                    displayMainMenu();
                    break;
                default:
                    System.out.println(THUT +"look at the list carefully");
                    checkChoice = true;
                    continue;
            }
        } while (checkChoice);

    }

    static void bookingManagement(){
        BookingServiceImpl bookingService = new BookingServiceImpl();
        ContractServiceImpl contractService = new ContractServiceImpl();
        PromotionServiceImpl promotionService = new PromotionServiceImpl();
        do {
            checkChoice = false;
            System.out.println(THUT +"1. Add new booking");
            System.out.println(THUT +"2. Display list booking");
            System.out.println(THUT +"3. Create new contracts");
            System.out.println(THUT +"4. Display list contract");
            System.out.println(THUT +"5. Edit contract");
            System.out.println(THUT +"6. Return main menu");
            System.out.println();
            System.out.println(THUT +"(-_-)___________(-_-)");
            System.out.println(THUT +"Enter your selection");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(THUT +"look at the list carefully");
                checkChoice = true;
                continue;
            }
            switch (choice){
                case 1:
                    bookingService.add();
                    contractService.init();
                    promotionService.readBookingFromFile();
                    checkChoice = true;
                    continue;
                case 2:
                    bookingService.display();
                    checkChoice = true;
                    continue;
                case 3:
                    contractService.add();
                    checkChoice = true;
                    continue;
                case 4:
                    contractService.display();
                    checkChoice = true;
                    continue;
                case 5:
                    contractService.editContract();
                    checkChoice = true;
                    continue;
                case 6:
                    displayMainMenu();
                    break;

                default:
                    System.out.println(THUT +"look at the list carefully");
                    checkChoice = true;
                    continue;
            }
        } while (checkChoice);

    }

    static void promotionManagement(){
        PromotionServiceImpl promotionService = new PromotionServiceImpl();
        do {
            checkChoice = false;
            System.out.println(THUT +"1. Display list customers use service");
            System.out.println(THUT +"2. Display list customers get voucher");
            System.out.println(THUT +"3. Return main menu");
            System.out.println();
            System.out.println(THUT +"(-_-)___________(-_-)");
            System.out.println(THUT +"Enter your selection");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(THUT +"look at the list carefully");
                checkChoice = true;
                continue;
            }
            switch (choice){
                case 1:
                    promotionService.displayCustomerByYear();
                    checkChoice = true;
                    continue;
                case 2:
                    promotionService.displayCustomersGetVoucher();
                    checkChoice = true;
                    continue;
                case 3:
                    displayMainMenu();
                    break;
                default:
                    System.out.println(THUT +"look at the list carefully");
                    checkChoice = true;
                    continue;
            }
        } while (checkChoice);

    }


}
