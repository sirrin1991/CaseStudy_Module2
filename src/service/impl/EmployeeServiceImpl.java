package service.impl;

import controllers.FuramaController;
import models.person.Employee;
import service.IPersonService;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EmployeeServiceImpl implements IPersonService {
    static List<Employee> listEmployee = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static List<String> indentityArray = new ArrayList<>();
    static final String CUSTOMER_PATH = "D:\\Test\\src\\data\\employee.csv";

    public EmployeeServiceImpl() {
        listEmployee = read();
    }

    static {
        List<Employee> intList = read();
    }

    public Employee creatEmployee() {
        String[] educationArr = {"intermediate", "collage", "university", "postUniversity"};
        String[] positionArr = {"receptionist", "serve", "monitor", "supervisor", "manager", "director"};
        String regexDate = "[0-9]{1,2}/[0-9]{1,2}/(20|19)[0-9]{2}";
        String regexPhone = "0[397][0378][0-9]{7}";
        String regexEmail = "[a-zA-Z][a-zA-Z0-9]+@gmail.com";
        String regexIndentityCardNumber = "[0-9]{9}";
        String inputDate;
        String inputEmail;
        String inputNumberPhone;
        String inputName;
        String inputGender;
        String inputIndetityCardNumber;
        String inputEducation;
        String inputPosition;
        String employeeId = null;
        boolean check;
        double inputSalary = 0;

        System.out.println(FuramaController.THUT + "Please input name: ");
        inputName = scanner.nextLine();

        do {
            check = false;
            System.out.println(FuramaController.THUT + "please input dateOfBirth (DD/MM/YY): ");
            inputDate = scanner.nextLine();

            if (!inputDate.matches(regexDate)) {
                check = true;
                System.out.println(FuramaController.THUT + "format date is error, please input again: ");
                continue;
            }
            String[] temp = inputDate.split("/");
            if (Integer.parseInt(temp[1]) < 0 || Integer.parseInt(temp[1]) > 12) {
                check = true;
                continue;
            }
            if (checkAge(temp)) {

            } else {
                System.out.println("age must less  than 100");
                check = true;
                continue;
            }
            if (Integer.parseInt(temp[1]) == 1 || Integer.parseInt(temp[1]) == 3 || Integer.parseInt(temp[1]) == 5 || Integer.parseInt(temp[1]) == 7 ||
                    Integer.parseInt(temp[1]) == 8 || Integer.parseInt(temp[1]) == 10 || Integer.parseInt(temp[1]) == 12) {
                if (Integer.parseInt(temp[0]) < 0 || Integer.parseInt(temp[0]) > 31) {
                    check = true;
                    continue;
                }
            } else if (Integer.parseInt(temp[1]) == 2) {
                if ((Integer.parseInt(temp[2]) % 4 == 0 && Integer.parseInt(temp[2]) % 100 != 0) || Integer.parseInt(temp[2]) % 400 == 0) {
                    if (Integer.parseInt(temp[0]) < 0 || Integer.parseInt(temp[0]) > 29) {
                        check = true;
                        continue;
                    }
                } else {
                    if (Integer.parseInt(temp[0]) < 0 || Integer.parseInt(temp[0]) > 28) {
                        check = true;
                        continue;
                    }
                }

            } else if (Integer.parseInt(temp[1]) > 0 || Integer.parseInt(temp[1]) < 13) {
                if (Integer.parseInt(temp[0]) < 0 || Integer.parseInt(temp[0]) > 30) {
                    check = true;
                    continue;
                }
            } else {
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
            check = true;
            System.out.println(FuramaController.THUT + "please Input indentity card number(9 numbers): ");
            inputIndetityCardNumber = scanner.nextLine();
            if (inputIndetityCardNumber.matches(regexIndentityCardNumber)) {
                for (String inputIndetity : indentityArray) {
                    if (inputIndetity.equals(inputIndetityCardNumber)) {
                        check = false;
                        break;
                    }
                }
            }
            if (check) {
                indentityArray.add(inputIndetityCardNumber);
                break;
            } else {
                System.out.println(FuramaController.THUT + "indentity card number is error, please input again");
            }
        } while (true);

        do {
            System.out.println(FuramaController.THUT + "please input number phone: ");
            inputNumberPhone = scanner.nextLine();
            if (inputNumberPhone.matches(regexPhone)) {
                break;
            }
            System.out.println(FuramaController.THUT + "Number phone is error,please input again: ");

        } while (true);

        do {
            System.out.println(FuramaController.THUT + "please input EMAIL: ");
            inputEmail = scanner.nextLine();
            if (inputEmail.matches(regexEmail)) {
                break;
            }
            System.out.println(FuramaController.THUT + "email is error format,please input again: ");
        } while (true);


        do {
            check = true;
            System.out.println(FuramaController.THUT + "please input Education(intermediate,collage,university,postUniversity: ");
            inputEducation = scanner.nextLine();
            for (String education : educationArr) {
                if (education.equals(inputEducation.toLowerCase())) {
                    check = false;
                    continue;
                }
            }
            if (!check) {
                break;
            }
            System.out.println(FuramaController.THUT + "education is error, please input again");
        } while (check);

        do {
            check = true;
            System.out.println(FuramaController.THUT + "please input position: ");
            System.out.println(FuramaController.THUT + "receptionist, serve, monitor," +
                    " supervisor, manager, director: ");
            inputPosition = scanner.nextLine();
            for (String position : positionArr) {
                if (position.toLowerCase().equals(inputPosition.toLowerCase())) {
                    check = false;
                    continue;
                }
            }
            if (!check) {
                break;
            }
            System.out.println(FuramaController.THUT + "position is error, please input again");
        } while (check);

        do {
            System.out.println(FuramaController.THUT + "Input SALARY: ");
            try {
                inputSalary = Double.parseDouble(scanner.nextLine());
                if (inputSalary < 0) {
                    System.out.println("can not negative, please input again");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(FuramaController.THUT + "Input SALARY  AGAIN: ");
                continue;
            }
            break;
        } while (true);
        for (String pos : positionArr) {
            if (inputPosition.toLowerCase().equals(pos)) {
                employeeId = inputPosition.substring(0, 3).toUpperCase() + randomId() + randomId() + randomId();
            }
        }
        Employee employee = new Employee(employeeId, inputName, inputDate, inputGender, inputIndetityCardNumber,
                inputNumberPhone, inputEmail, inputEducation, inputPosition, inputSalary);
        return employee;


    }

    @Override
    public void edit() {
        System.out.println(FuramaController.THUT + "Please input customer's Id what you want to edit");
        String customerId = scanner.nextLine();
        Employee tempEmployee;
        boolean check = false;
        for (int i = 0; i < listEmployee.size(); i++) {
            check = false;
            if (listEmployee.get(i).getId().toLowerCase().equals(customerId.toLowerCase())) {
                String tempId = listEmployee.get(i).getId().substring(3);
                tempEmployee = creatEmployee();
                tempId = tempEmployee.getId().substring(0, 3) + tempId;
                tempEmployee.setId(tempId);
                listEmployee.set(i, tempEmployee);
                write();
                check = true;
                write();
                break;
            }
        }
        if (!check) {
            System.out.println(FuramaController.THUT + "input dose not match");
        }
    }

    @Override
    public void add() {
        Employee employee = creatEmployee();
        listEmployee.add(employee);
        write();
    }

    @Override
    public void display() {
        if (listEmployee.size() > 0) {
            System.out.println();
            System.out.println(FuramaController.THUT + "List Employee");
            System.out.println();
            System.out.println(FuramaController.HALF_THUT + "----------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-28s%-12s%-12s%-15s%-12s%-12s%-12s%-18s%-12s%-15s%-12s%-12s", "", "|     Id",
                    "|    name", "|   birth day", "|   gender", "| identity",
                    "|   phone", "|     email", "| education", "| position", "|   salary", "|");
            System.out.println();
            System.out.println(FuramaController.HALF_THUT + "----------------------------------------------------------------------------------------------------------------------------------");
            for (Employee ep : listEmployee) {
                System.out.printf("%-28s%-12s%-12s%-15s%-12s%-12s%-12s%-18s%-12s%-15s%-12s%-12s", " ",
                        "| " + ep.getId(), "| " + ep.getName(), "| " + ep.getDayOfBirth(), "| " + ep.getGender(), "| " + ep.getIdentityCardNumber(),
                        "| " + ep.getNumberPhone(), "| " + ep.getEmail(), "| " + ep.getEducation(), "| " + ep.getPosition(), "| " + ep.getSalary(), "|");
                System.out.println();
                System.out.println(FuramaController.HALF_THUT + "----------------------------------------------------------------------------------------------------------------------------------");
            }
            System.out.println();

        } else {
            System.out.println(FuramaController.THUT + "(^_^_________________________(^_^)");
            System.out.println(FuramaController.THUT + "not exist data,please add new data");
            System.out.println(FuramaController.THUT + "(^_^_________________________(^_^)");
            System.out.println();
        }


    }

    static void write() {
        File file = new File(CUSTOMER_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Employee employee : listEmployee) {
                bufferedWriter.write(employee.getId() + "," + employee.getName() + "," + employee.getDayOfBirth() + "," +
                        employee.getGender() + "," + employee.getIdentityCardNumber() + "," + employee.getNumberPhone() + "," +
                        employee.getEmail() + "," + employee.getEducation() + "," + employee.getPosition() + "," + employee.getSalary());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Employee> read() {
        List<Employee> listEmployee = new ArrayList<>();
        File file = new File(CUSTOMER_PATH);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] dataArray = data.split(",");
                Employee employee = new Employee(dataArray[0], dataArray[1], dataArray[2], dataArray[3], dataArray[4], dataArray[5],
                        dataArray[6], dataArray[7], dataArray[8], Double.parseDouble(dataArray[9]));
                indentityArray.add(employee.getIdentityCardNumber());
                listEmployee.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listEmployee;
    }


    int randomId() {
        int random = 0;
        Random rd = new Random();
        random = rd.nextInt(10);
        return random;
    }

    static boolean checkAge(String[] arr) {
        LocalDate dayOfBirth = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
        LocalDate now = LocalDate.now();
        long age = Period.between(dayOfBirth, now).getYears();
        return age < 100;
    }
}