package service.impl;

import controllers.FuramaController;
import models.person.Customer;
import service.IPersonService;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class CustomerServiceImpl implements IPersonService {

    public static final String CUSTOMER_FILE = "D:\\Test\\src\\data\\customers.csv";
    private static List<Customer> customerTypeList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static List<String> indentityArr = new ArrayList<>();
    public CustomerServiceImpl() {
        customerTypeList = readFromFile();

    }

    static {
        List<Customer> intList = readFromFile();

    }

    @Override
    public void edit() {
        String inputID;
        Customer customer;
        System.out.println(FuramaController.THUT + "Please input customer's Id what you want to edit");
        inputID = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < customerTypeList.size(); i++) {
            check = false;
            if (inputID.toUpperCase().equals(customerTypeList.get(i).getId())) {
                String postId = customerTypeList.get(i).getId().substring(3, 6);
                customer = createCustomer();
                String preID = customer.getId().substring(0, 3);
                customer.setId(preID + postId);
                customerTypeList.set(i, customer);
                check = true;
                break;
            }
        }
        write();
        if (!check) {
            System.out.println(FuramaController.THUT + "input dose not match");
        }
    }

    @Override
    public void add() {
        Customer customer = createCustomer();
        customerTypeList.add(customer);
        write();
    }

    @Override
    public void display() {
        if (customerTypeList.size() >1) {

            System.out.println();
            System.out.println(FuramaController.THUT+"List Customers");
            System.out.println();
            System.out.println(FuramaController.HALF_THUT+"-------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-28s%-12s%-12s%-15s%-12s%-12s%-12s%-18s%-12s%-15s%-12s","","|     Id",
                    "|    name","|   birth day","|   gender","| identity",
                    "|   phone","|     email","| type","| address","|");
            System.out.println();
            System.out.println(FuramaController.HALF_THUT+"-------------------------------------------------------------------------------------------------------------------------");
            for(Customer customer : customerTypeList){
                System.out.printf("%-28s%-12s%-12s%-15s%-12s%-12s%-12s%-18s%-12s%-15s%-12s"," ",
                        "| "+customer.getId(),"| "+customer.getName(),"| "+customer.getDayOfBirth(),"| "+customer.getGender(),"| "+customer.getIdentityCardNumber(),
                        "| "+customer.getNumberPhone(),"| "+customer.getEmail(),"| "+customer.getCustomerType(),"| "+customer.getAddress(),"|");
                System.out.println();
                System.out.println(FuramaController.HALF_THUT+"-------------------------------------------------------------------------------------------------------------------------");
            }
            System.out.println();

        }else {
            System.out.println(FuramaController.THUT+"(^_^_________________________(^_^)");
            System.out.println(FuramaController.THUT+"not exist data,please add new data");
            System.out.println(FuramaController.THUT+"(^_^_________________________(^_^)");
            System.out.println();
        }


    }

    public int random() {
        return (int) Math.round(Math.random() * 10);
    }

    public Customer createCustomer() {
        String[] customerType = {"Diamond", "Platinum", "Gold", "Silver", "Member"};
        final String REGEX_EMAIL = "[a-zA-Z][\\w]*@gmail.com";
        final String REGEX_PHONE = "(093|095|097)[0-9]{7}";
        final String REGEX_BIRTHDAY = "[0-9]{1,2}/[0-9]{1,2}/(20|19)[0-9]{2}";
        final String REGEX_IDENTITY = "[0-9]{9}";
        boolean check = false;
        String id = null;
        String fullName;
        String dayOfBirth;
        String inputGender;
        String identityCardNo;
        String phoneNo;
        String email;
        String guestType;
        String address;
        Scanner scanner = new Scanner(System.in);


        System.out.println(FuramaController.THUT + "Please input name:");
        fullName = scanner.nextLine();

        do {
            check = false;
            System.out.println(FuramaController.THUT + "please input dateOfBirth (DD/MM/YYYY): ");
            dayOfBirth = scanner.nextLine();
            if (dayOfBirth.matches(REGEX_BIRTHDAY)) {
                String[] dayOfBirthArray = dayOfBirth.split("/");
                if ((Integer.parseInt(dayOfBirthArray[1])) < 0 || (Integer.parseInt(dayOfBirthArray[1])) > 12) {
                    System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                    check = true;
                } else if (!checkAge(dayOfBirthArray)) {
                    System.out.println(FuramaController.THUT + "age must less  than 100 ");
                    check = true;
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
                                check = true;
                            }
                            break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            if (Integer.parseInt(dayOfBirthArray[0]) < 0 || (Integer.parseInt(dayOfBirthArray[0]) > 30)) {
                                System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                                check = true;
                            }
                            break;
                        case 2:
                            if ((Integer.parseInt(dayOfBirthArray[2]) % 4 == 0 && Integer.parseInt(dayOfBirthArray[2]) % 100 != 0) || Integer.parseInt(dayOfBirthArray[2]) % 400 == 0) {
                                if (Integer.parseInt(dayOfBirthArray[0]) < 0 || Integer.parseInt(dayOfBirthArray[0]) > 29) {
                                    check = true;
                                    System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                                }
                            } else {
                                if (Integer.parseInt(dayOfBirthArray[0]) < 0 || Integer.parseInt(dayOfBirthArray[0]) > 28) {
                                    check = true;
                                    System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                                }
                            }
                            break;
                    }
                }
            }else {
                check = true;
            }
        } while (check);

        do {
            System.out.println(FuramaController.THUT + "please  input gender(man or woman): ");
            inputGender = scanner.nextLine();
            if (inputGender.toLowerCase().equals("man") ||
                    inputGender.toLowerCase().equals("woman")) {
                break;
            } else {
                System.out.println(FuramaController.THUT + "gender be must man or woman");
            }

        } while (true);

        do {
            check = false;
            System.out.println(FuramaController.THUT + "please Input indentity card number(9 numbers): ");
            identityCardNo = scanner.nextLine();
            if (identityCardNo.matches(REGEX_IDENTITY)) {
                for(String indentity: indentityArr){
                    if(indentity.equals(identityCardNo)){
                        System.out.println("this indentity has existed,please input again");
                        check = true;
                        break;
                    }
                }
                if(!check){
                    indentityArr.add(identityCardNo);
                    break;
                }
            }else {
                System.out.println(FuramaController.THUT + "indentity card number is error, please input again");
                check = true;
            }

        } while (true);


        do {
            System.out.println(FuramaController.THUT + "please input number phone: ");
            phoneNo = scanner.nextLine();
            if (phoneNo.matches(REGEX_PHONE)) {
                break;
            }
            System.out.println(FuramaController.THUT + "Number phone is error,please input again: ");
        } while (true);

        do {
            System.out.println(FuramaController.THUT + "please input EMAIL: ");
            email = scanner.nextLine();
            if (email.matches(REGEX_EMAIL)) {
                break;
            }
            System.out.println(FuramaController.THUT + "email is error format,please input again: ");
        } while (true);

        loopWhile:
        while (true) {
            System.out.println(FuramaController.THUT + "please input guest type(Diamond,Platinum, Gold, Silver, Member: ");
            guestType = scanner.nextLine();
            for (String s : customerType) {
                if (guestType.toLowerCase().equals(s.toLowerCase())) {
                    break loopWhile;
                }
            }
            System.out.println(FuramaController.THUT + "guest type is error, please input again");
        }

        System.out.println(FuramaController.THUT + "please input address");
        address = scanner.nextLine();

        for (String customerTp : customerType) {
            if (guestType.toLowerCase().equals(customerTp.toLowerCase())) {
                id = customerTp.substring(0, 3).toUpperCase() + random() + random() + random();
            }
        }
        return new Customer(id, fullName, dayOfBirth, inputGender, identityCardNo,
                phoneNo, email, guestType, address);
    }

    public static void write() {
        File customerProfile = new File(CUSTOMER_FILE);
        try {
            FileWriter fw = new FileWriter(customerProfile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Customer c : customerTypeList) {
                bw.write(c.getId() + "," + c.getName() + "," + c.getDayOfBirth() + ","
                        + c.getGender() + "," + c.getIdentityCardNumber() + "," + c.getNumberPhone() + ","
                        + c.getEmail() + "," + c.getCustomerType() + "," + c.getAddress());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> readFromFile() {
        List<Customer> dataList = new ArrayList<>();
        File readCustomerProfile = new File(CUSTOMER_FILE);
        FileReader fileReader;
        try {
            fileReader = new FileReader(readCustomerProfile);
            BufferedReader br = new BufferedReader(fileReader);
            String data;
            while ((data = br.readLine()) != null) {
                String[] dataA = data.split(",");
                Customer customer = new Customer(dataA[0], dataA[1], dataA[2], dataA[3],
                        dataA[4], dataA[5], dataA[6],
                        dataA[7], dataA[8]);
                indentityArr.add(customer.getIdentityCardNumber());
                dataList.add(customer);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    static boolean checkAge(String[] arr) {
        LocalDate birthdate = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
        LocalDate now = LocalDate.now();
        long age = Period.between(birthdate, now).getYears();
        return age < 100;
    }
}