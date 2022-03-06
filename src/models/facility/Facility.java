package models.facility;

public abstract class Facility {
    private int quantityOfBooking;
    private String serviceName;
    private String codeService;
    private double areaUsable;
    private int rent;
    private int maxPeople;
    private String rentType;

    public Facility() {
    }

    public Facility(String codeService, String nameService, double areaUsable, int rent, int maxPeople, String rentType) {
        this.codeService = codeService;
        this.serviceName = nameService;
        this.areaUsable = areaUsable;
        this.rent = rent;
        this.maxPeople = maxPeople;
        this.rentType = rentType;
        this.quantityOfBooking = 0;
    }

    public int getQuantityOfBooking() {
        return quantityOfBooking;
    }

    public void setQuantityOfBooking(int quantityOfBooking) {
        this.quantityOfBooking = quantityOfBooking;
    }

    public String getCodeService() {
        return codeService;
    }

    public void setCodeService(String codeService) {
        this.codeService = codeService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getAreaUsable() {
        return areaUsable;
    }

    public void setAreaUsable(double areaUsable) {
        this.areaUsable = areaUsable;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    @Override
    public String toString() {
        return "codeService: " + codeService +
                ",serviceName :" + serviceName +
                ", areaUsable :" + areaUsable +
                ", rent :" + rent +
                ", maxPeople :" + maxPeople +
                ", rentType :" + rentType;
    }
}
