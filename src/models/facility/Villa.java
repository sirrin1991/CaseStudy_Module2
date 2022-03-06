package models.facility;

import java.util.Objects;

public class Villa extends Facility {
    private String roomStandard;
    private double poolArea;
    private int quantityOfFloor;

    public Villa() {
    }

    public Villa(String codeService, String serviceName, double areaUsable, int rent, int maxPeople,
                 String rentType, String roomStandard, double poolArea, int quantityOfFloor) {
        super(codeService, serviceName, areaUsable, rent, maxPeople, rentType);
        this.roomStandard = roomStandard;
        this.poolArea = poolArea;
        this.quantityOfFloor = quantityOfFloor;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getQuantityOfFloor() {
        return quantityOfFloor;
    }

    public void setQuantityOfFloor(int quantityOfFloor) {
        this.quantityOfFloor = quantityOfFloor;
    }

    @Override
    public String toString() {
        return "Villa {" +
                super.toString() +
                " ,serviceName : " + this.getServiceName() +
                " ,roomStandard :" + roomStandard +
                ", poolArea :" + poolArea +
                ", quantityOfFloor :" + quantityOfFloor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villa villa = (Villa) o;
        return Objects.equals(roomStandard, villa.roomStandard) && Objects.equals(getServiceName(), villa.getServiceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomStandard, super.getServiceName());
    }
}
