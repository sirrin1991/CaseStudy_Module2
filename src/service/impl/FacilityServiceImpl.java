package service.impl;

import controllers.FuramaController;
import models.facility.Facility;
import models.facility.House;
import models.facility.Room;
import models.facility.Villa;
import service.IFacilityService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FacilityServiceImpl implements IFacilityService {
    static Map<Facility,Integer> facilityMap = new LinkedHashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static {
        readData();
    }

    public FacilityServiceImpl(){
    }

    @Override
    public void displayListMaintenance() {
        BookingServiceImpl bookingService = new BookingServiceImpl();
        List<Facility> listMaintain = bookingService.readMaintain();
        boolean checkVilla = false;
        boolean checkRoom = false;
        boolean checkHouse = false;
        if(listMaintain.size()>0){
            for(Facility facility:listMaintain){
                if(facility instanceof Villa){
                    checkVilla = true;
                }
                if(facility instanceof House){
                    checkHouse = true;
                }
                if(facility instanceof Room){
                    checkRoom = true;
                }
            }

            if(checkVilla){
                System.out.println(FuramaController.THUT+"----------");
                System.out.println(FuramaController.THUT+"List Villa");
                System.out.println();
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-35s%-12s%-12s%-15s%-12s%-12s%-12s%-14s%-12s%-12s%-12s","","|    Id",
                        "|  service","|  area usable","|    rent","|  people",
                        "| rent type","|  room stand","| pool area","|   floor","|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------------------------------------------------------");
                for(Facility facility : listMaintain){
                    if(facility instanceof Villa){
                        Villa villa = (Villa) facility;
                        System.out.printf("%-35s%-12s%-12s%-15s%-12s%-12s%-12s%-14s%-12s%-12s%-12s","",
                                "| "+villa.getCodeService(),"| "+villa.getServiceName(),"| "+villa.getAreaUsable(),"| "+villa.getRent(),"| "+villa.getMaxPeople(),
                                "| "+villa.getRentType(),"| "+villa.getRoomStandard(),"| "+villa.getPoolArea(),"| "+villa.getQuantityOfFloor(),"|");
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t\t\t----------------------------------------------------------------------------------------------------------------");
                    }

                }
            }

            if(checkHouse){

                System.out.println(FuramaController.THUT+"----------");

                System.out.println(FuramaController.THUT+"List House");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-39s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-12s%-12s","","|     Id",
                        "|  service","|  area usable","|  rent","|   people",
                        "|rent type","|room stand","|   floor","|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                for(Facility facility : listMaintain){
                    if(facility instanceof House){
                        House house = (House) facility;
                        System.out.printf("%-39s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-12s%-12s","",
                                "| "+house.getCodeService(),"| "+house.getServiceName(),"| "+house.getAreaUsable(),"| "+house.getRent(),"| "+house.getMaxPeople(),
                                "| "+house.getRentType(),"| "+house.getRoomStandard(),"| "+house.getQuantityOfFloor(),"|");
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                    }

                }
            }


            if(checkRoom){
                System.out.println();
                System.out.println(FuramaController.THUT+"---------");
                System.out.println(FuramaController.THUT+"List Room");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-43s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-35s","","|    Id",
                        "| service","|  area usable","|   rent","|   people",
                        "| rent type","| free ","|");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                for(Facility facility : listMaintain){
                    if(facility instanceof Room){
                        Room room = (Room) facility;
                        System.out.printf("%-43s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-35s","",
                                "| "+room.getCodeService(),"| "+room.getServiceName(),"| "+room.getAreaUsable(),"| "+room.getRent(),
                                "| "+room.getMaxPeople(), "| "+room.getRentType(),"| "+room.getFreeService(),"|");
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                    }

                }
                System.out.println();
            }

        }else{
            System.out.println(FuramaController.THUT+"(^_^)_______________________________________________(^_^)");
            System.out.println(FuramaController.THUT+"There is not maintenance service. you can add every thing");
            System.out.println(FuramaController.THUT+"(^_^)_______________________________________________(^_^)");
            System.out.println();
        }

    }

    public void add(int choice) {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        String regexText = "[A-Z][a-z]*";
        if(choice == 1){
            String[] villaStandArr = {"Spanish", "Arabic", "Combinative",
                    "Mediterranean", "Contemporary", "Classic design"};
            Double poolArea = null;
            String villaStand;
            int quantityOfFloor = 0;
            String[] propertyOfFacility = createPropertyOfFacility("villa");
            do{
                check = true;
//                int codeFirstChar;
                System.out.println(FuramaController.THUT+"please input villa stand");
                System.out.println(FuramaController.THUT+"please only choose Spanish, Arabic, Combinative, " +
                        "Mediterranean, Contemporary, Classic Design");
                villaStand = sc.nextLine();
                if(villaStand.matches(regexText)){
                    for(String stand : villaStandArr){
                        if(stand.toLowerCase().equals(villaStand.toLowerCase())){
                            check =false;
                        }
                    }
                }

                if(check){
                    System.out.println(FuramaController.THUT+"stand error,please input the first letter to upper");
                }
            }while(check);

            do {
                check = false;
                System.out.println(FuramaController.THUT+"please input area of pool(m2)");
                try{
                    poolArea = Double.parseDouble(sc.nextLine());
                }catch (Exception e){
                    check = true;
                    System.out.println(FuramaController.THUT+"error, please input a number");
                    continue;
                }
                if(poolArea<30){
                    System.out.println(FuramaController.THUT+"please input a number bigger than 30");
                    check = true;
                }
            }while(check);

            do {
                check = false;
                System.out.println(FuramaController.THUT+"please input quantity of floors");
                try{
                    quantityOfFloor = Integer.parseInt(sc.nextLine());

                }catch (Exception e){
                    check = true;
                    System.out.println(FuramaController.THUT+"error, please input a number");
                    continue;
                }
                if(quantityOfFloor<0){
                    System.out.println(FuramaController.THUT+"please input a positive number");
                    check = true;
                }
            }while(check);

            Villa villa = new Villa(propertyOfFacility[0],propertyOfFacility[1], Double.parseDouble(propertyOfFacility[2]),
                    Integer.parseInt(propertyOfFacility[3]),
                    Integer.parseInt(propertyOfFacility[4]),propertyOfFacility[5],villaStand,poolArea,quantityOfFloor);
            updateService(villa);
            write();
        }else if(choice==2){

            String[] houseStandArr = {"Superior", "deluxe", "premierDeluxe", "studio"};
            String[] propertyOfFacility = createPropertyOfFacility("house");
            String houseStandard;
            int quantityOfFloor = 0;

            do{
                check = true;
                System.out.println(FuramaController.THUT+"please input villa stand");
                System.out.println(FuramaController.THUT+"please only choose :Superior, Deluxe, PremierDeluxe, Studio");
                houseStandard = sc.nextLine();
                if(houseStandard.matches(regexText)){
                    for(String stand : houseStandArr){
                        if(stand.toLowerCase().equals(houseStandard.toLowerCase())){
                            check =false;
                            break;
                        }
                    }
                }
                if(check){
                    System.out.println(FuramaController.THUT+"stand error, please input the first letter to upper");
                }
            }while(check);


            do {
                check = false;
                System.out.println(FuramaController.THUT+"please input quantity of floors");
                try{
                    quantityOfFloor = sc.nextInt();
                }catch (Exception e){
                    check = true;
                    System.out.println(FuramaController.THUT+"error, please input a number");
                    continue;
                }
                if(quantityOfFloor<0){
                    System.out.println(FuramaController.THUT+"please input a positive number");
                    check = true;
                }
            }while(check);

            House house = new House(propertyOfFacility[0],propertyOfFacility[1], Double.parseDouble(propertyOfFacility[2]),
                    Integer.parseInt(propertyOfFacility[3]),Integer.parseInt(propertyOfFacility[4]),
                    propertyOfFacility[5],houseStandard,quantityOfFloor);
            updateService(house);
            write();

        }else {
            String[] freeServiceArr = {"water","whore","breakfast"};
            String[] propertyOfFacility = createPropertyOfFacility("room");
            String freeService;
            System.out.println(FuramaController.THUT+"please input free service:");
            System.out.println(FuramaController.THUT+"please only input water,whore,breakfast");
            do{
//                check = check;
                freeService = sc.nextLine();
                for(String service:freeServiceArr){
                    if(service.equals(freeService.toLowerCase())){
                        check = false;
                        break;
                    }
                }
                if(check){
                    System.out.println(FuramaController.THUT+"please only input water,whore,breakfast");
                }

            }while(check);

            Room room = new Room(propertyOfFacility[0],propertyOfFacility[1], Double.parseDouble(propertyOfFacility[2]),
                    Integer.parseInt(propertyOfFacility[3]),Integer.parseInt(propertyOfFacility[4]),
                    propertyOfFacility[5],freeService);
            updateService(room);
            write();
        }
    }

    @Override
    public void display() {
        if(facilityMap.size()>0){
            System.out.println(FuramaController.THUT+"----------");
            System.out.println(FuramaController.THUT+"List Villa");
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-35s%-12s%-12s%-15s%-12s%-12s%-12s%-14s%-12s%-12s%-12s","","|    Id",
                    "|  service","|  area usable","|    rent","|  people",
                    "| rent type","|  room stand","| pool area","|   floor","|");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------------------------------------------------------");
            for(Map.Entry<Facility,Integer> entry: facilityMap.entrySet()){
                if(entry.getKey() instanceof Villa){
                    Villa villa = (Villa) entry.getKey();
                    System.out.printf("%-35s%-12s%-12s%-15s%-12s%-12s%-12s%-14s%-12s%-12s%-12s","",
                            "| "+villa.getCodeService(),"| "+villa.getServiceName(),"| "+villa.getAreaUsable(),"| "+villa.getRent(),"| "+villa.getMaxPeople(),
                            "| "+villa.getRentType(),"| "+villa.getRoomStandard(),"| "+villa.getPoolArea(),"| "+villa.getQuantityOfFloor(),"|");
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t\t\t\t----------------------------------------------------------------------------------------------------------------");
                }

            }
            System.out.println(FuramaController.THUT+"----------");

            System.out.println(FuramaController.THUT+"List House");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-39s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-12s%-12s","","|     Id",
                    "|  service","|  area usable","|  rent","|   people",
                    "|rent type","|room stand","|   floor","|");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
            for(Map.Entry entry: facilityMap.entrySet()){
                if(entry.getKey() instanceof House){
                    House house = (House) entry.getKey();
                    System.out.printf("%-39s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-12s%-12s","",
                            "| "+house.getCodeService(),"| "+house.getServiceName(),"| "+house.getAreaUsable(),"| "+house.getRent(),"| "+house.getMaxPeople(),
                            "| "+house.getRentType(),"| "+house.getRoomStandard(),"| "+house.getQuantityOfFloor(),"|");
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------------------");
                }

            }
            System.out.println();
            System.out.println(FuramaController.THUT+"---------");
            System.out.println(FuramaController.THUT+"List Room");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-43s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-35s","","|    Id",
                    "| service","|  area usable","|   rent","|   people",
                    "| rent type","| free ","|");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
            for(Map.Entry entry: facilityMap.entrySet()){
                if(entry.getKey() instanceof Room){
                    Room room = (Room) entry.getKey();
                    System.out.printf("%-43s%-12s%-12s%-15s%-12s%-12s%-12s%-12s%-35s","",
                            "| "+room.getCodeService(),"| "+room.getServiceName(),"| "+room.getAreaUsable(),"| "+room.getRent(),
                            "| "+room.getMaxPeople(), "| "+room.getRentType(),"| "+room.getFreeService(),"|");
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t---------------------------------------------------------------------------------------");
                }

            }
            System.out.println();

        }else {
            System.out.println(FuramaController.THUT+"(^_^)_______________________________________________(^_^)");
            System.out.println(FuramaController.THUT+"There is not service. please add");
            System.out.println(FuramaController.THUT+"(^_^)_______________________________________________(^_^)");
            System.out.println();
        }



    }

    String[] createPropertyOfFacility(String service){
        String regexVilla = "(Villa-)[0-9]{1,}";
        String regexHouse = "(House-)[0-9]{1,}";
        String regexRoom = "(Room-)[0-9]{1,}";
        String regexText = "[A-Z][a-z]*";
        String[] facility = new String[6];
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        String codeService;
        String nameService;
        double areaUsable;
        int rent = 0;
        int maxPeople = 0;
        String rentType;
        String[] rentTypeArr = {"year","month","day","hour"};

        do{
            if(service.equals("villa")){
                codeService = "SVVL-"+randomId()+randomId()+randomId()+randomId();
                do{
                    check = true;
                    System.out.println(FuramaController.THUT+"Please input name service");
                    System.out.println(FuramaController.THUT+"please input format Villa-number");
                    nameService = scanner.nextLine();
                    if(nameService.matches(regexVilla)){
                        check = false;
                        break;
                    }

                    if(check){
                        System.out.println(FuramaController.THUT+"please input carefully, format is Villa-number");
                    }
                }while(check);

                break;
            }else  if(service.equals("house")){

                codeService = "SVHO-"+randomId()+randomId()+randomId()+randomId();
                do{
                    check = true;
                    System.out.println(FuramaController.THUT+"Please input name service");
                    System.out.println(FuramaController.THUT+"please input format House-number");
                    nameService = scanner.nextLine();
                    if(nameService.matches(regexHouse)){
                        check = false;
                        break;
                    }

                    if(check){
                        System.out.println(FuramaController.THUT+"please input carefully,format is House-number");
                    }
                }while (check);
                break;
            }else{
                codeService = "SVRO-"+randomId()+randomId()+randomId()+randomId();
                do{
                    check = true;
                    System.out.println(FuramaController.THUT+"Please input name service");
                    System.out.println(FuramaController.THUT+"please input format Room-number");
                    nameService = scanner.nextLine();
                    if(nameService.matches(regexRoom)){
                        check = false;
                        break;
                    }

                    if(check){
                        System.out.println(FuramaController.THUT+"please input carefully,format is Room-number");
                    }
                }while (check);
                break;
            }
        }while(true);

        do{
            System.out.println(FuramaController.THUT+"please input area of service(m2)");
            try{
                areaUsable = Double.parseDouble(scanner.nextLine());
            }catch (Exception e){
                System.out.println(FuramaController.THUT+"input error,please input again");
                continue;
            }
            if(areaUsable<30){
                System.out.println(FuramaController.THUT+"the area must bigger than 30");
                continue;
            }
            break;
        }while(true);

        do{
            System.out.println(FuramaController.THUT+"please input rent price");
            try{
                rent = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println(FuramaController.THUT+"input error,please input again");
                continue;
            }
            if(rent<0){
                System.out.println(FuramaController.THUT+"can not input negative price");
                continue;
            }
            break;
        }while(true);

        do{
            System.out.println(FuramaController.THUT+"please input quantity of people");
            try{
                maxPeople = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println(FuramaController.THUT+"input error,please input again");
                continue;
            }
            if(maxPeople<0||maxPeople>20){
                System.out.println(FuramaController.THUT+"people must bigger than 0 and less 20");
                continue;
            }
            break;
        }while(true);

        do{
            int codeFirstChar;
            check = true;
            System.out.println(FuramaController.THUT+"please input rent type");
            System.out.println(FuramaController.THUT+"please only input : Year,Month,Day,Hour");
            rentType = scanner.nextLine();
            if(rentType.matches(regexText)){
                for(String type: rentTypeArr){
                    if(type.toLowerCase().equals(rentType.toLowerCase())){
                        check = false;
                        break;
                    }
                }
            }

            if(check){
                System.out.println(FuramaController.THUT+"the first letter must upper,please input again");
            }
        }while(check);

        facility[0] = codeService;
        facility[1] = nameService;
        facility[2] = String.valueOf(areaUsable);
        facility[3] = String.valueOf(rent);
        facility[4] = String.valueOf(maxPeople);
        facility[5] = rentType;
        return  facility;
    }

    int randomId(){
        int random = 0;
        Random rd = new Random();
        random = rd.nextInt(10);
        return random;
    }

    static void readData() {
        for(Map.Entry<Facility,Integer> entry : readVilla().entrySet()){
            facilityMap.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Facility,Integer> entry : readHouse().entrySet()){
            facilityMap.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Facility,Integer> entry : readRoom().entrySet()){
            facilityMap.put(entry.getKey(), entry.getValue());
        }

    }

    static Map<Facility,Integer> readVilla(){
        Map<Facility,Integer> initMap = new LinkedHashMap<>();
        final String PATH_VILLA = "/Users/nguyenhoang/Desktop/codegym/C1021G1/module2/src/faramaResort/data/villa.csv";
        try {
            FileReader fileReader = new FileReader(PATH_VILLA);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine())!=null){
                String[] villaArray = data.split(",");
                Villa villa = new Villa(villaArray[0],villaArray[1],Double.parseDouble(villaArray[2]),Integer.parseInt(villaArray[3]),
                        Integer.parseInt(villaArray[4]),villaArray[5],villaArray[6],
                        Double.parseDouble(villaArray[7]),Integer.parseInt(villaArray[8]));
                initMap.put(villa,villa.getQuantityOfBooking());
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  initMap;
    }

    static Map<Facility,Integer> readHouse(){
        Map<Facility,Integer> initMap = new LinkedHashMap<>();
        final String PATH_HOUSE = "D:\\Test\\src\\data\\house.csv";
        try {
            FileReader fileReader = new FileReader(PATH_HOUSE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine())!=null){
                String[] houseArray = data.split(",");
                House house = new House(houseArray[0],houseArray[1],Double.parseDouble(houseArray[2]),Integer.parseInt(houseArray[3]),
                        Integer.parseInt(houseArray[4]),houseArray[5],houseArray[6],
                        Integer.parseInt(houseArray[7]));
                initMap.put(house,house.getQuantityOfBooking());
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  initMap;
    }

    static Map<Facility,Integer> readRoom(){
        Map<Facility,Integer> initMap = new LinkedHashMap<>();
        final String PATH_ROOM = "D:\\Test\\src\\data\\room.csv";
        try {
            FileReader fileReader = new FileReader(PATH_ROOM);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = null;
            while ((data = bufferedReader.readLine())!=null){
                String[] roomArray = data.split(",");
                Room room= new Room(roomArray[0],roomArray[1],Double.parseDouble(roomArray[2]),Integer.parseInt(roomArray[3]),
                        Integer.parseInt(roomArray[4]),roomArray[5],roomArray[6]);
                initMap.put(room,room.getQuantityOfBooking());
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  initMap;
    }

    static void write(){
        Map<Villa,Integer> villaIntegerMap = new LinkedHashMap<>();
        Map<House,Integer> houseIntegerMap = new LinkedHashMap<>();
        Map<Room,Integer> roomIntegerMap = new LinkedHashMap<>();
        for(Map.Entry<Facility,Integer> entry : facilityMap.entrySet()){
            if(entry.getKey() instanceof Villa){
                Villa villa = (Villa) entry.getKey();
                villaIntegerMap.put(villa,entry.getValue());
            }else if(entry.getKey() instanceof House){
                House house = (House) entry.getKey();
                houseIntegerMap.put(house,entry.getValue());
            }else {
                Room room = (Room) entry.getKey();
                roomIntegerMap.put(room,entry.getValue());
            }
        }
        writeVilla(villaIntegerMap);
        writeHouse(houseIntegerMap);
        writeRoom(roomIntegerMap);
    }

    static void writeVilla(Map<Villa,Integer> villaMap){
        final String PATH_VILLA = "D:\\Test\\src\\data\\villa.csv";
        File file = new File(PATH_VILLA);
        try {
            FileWriter fileReader = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
            String data = null;
            for(Map.Entry<Villa,Integer> entry: villaMap.entrySet()){
                Villa villa  = entry.getKey();
                bufferedWriter.write(villa.getCodeService()+","+villa.getServiceName()+","+villa.getAreaUsable()+","+
                        villa.getRent()+","+villa.getMaxPeople()+","+villa.getRentType()+","+
                        villa.getRoomStandard()+","+villa.getPoolArea()+","+villa.getQuantityOfFloor());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static void writeHouse(Map<House,Integer> houseMap){
        final String PATH_HOUSE = "D:\\Test\\src\\data\\house.csv";
        File file = new File(PATH_HOUSE);
        try {
            FileWriter fileReader = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
            for(Map.Entry<House,Integer> entry : houseMap.entrySet()){
                House house = entry.getKey();
                bufferedWriter.write(house.getCodeService()+","+house.getServiceName()+","+house.getAreaUsable()+","+
                        house.getRent()+","+house.getMaxPeople()+","+house.getRentType()+","+
                        house.getRoomStandard()+","+house.getQuantityOfFloor());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void writeRoom(Map<Room,Integer> roomMap){
        final String PATH_ROOOM = "D:\\Test\\src\\data\\room.csv";
        File file = new File(PATH_ROOOM);
        try {
            FileWriter fileReader = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
            for(Map.Entry<Room,Integer> entry: roomMap.entrySet()){
                Room room = entry.getKey();
                bufferedWriter.write(room.getCodeService()+","+room.getServiceName()+","+room.getAreaUsable()+","+
                        room.getRent()+","+room.getMaxPeople()+","+room.getRentType()+","+ room.getFreeService());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateService(Facility facility){
        if(facilityMap.containsKey(facility)){
            System.out.println("this service has existed in list");
        }else facilityMap.put(facility,0);
    }

}