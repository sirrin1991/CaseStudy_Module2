package models.facility;

import java.util.Objects;

public class House extends Facility {
    private String roomStandard;
    private int quantityOfFloor;

    public House() {
    }

    public House(String codeService,
                 String serviceName,
                 double areaUsable,
                 int rent,
                 int maxPeople,
                 String rentType,
                 String roomStandard,
                 int quantityOfFloor) {
        super(codeService, serviceName, areaUsable, rent, maxPeople, rentType);
        this.roomStandard = roomStandard;
        this.quantityOfFloor = quantityOfFloor;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public int getQuantityOfFloor() {
        return quantityOfFloor;
    }

    public void setQuantityOfFloor(int quantityOfFloor) {
        this.quantityOfFloor = quantityOfFloor;
    }

    @Override
    public String toString() {
        return "House {" +
                super.toString() +
                " ,roomStandard :" + roomStandard +
                ", quantityOfFloor :" + quantityOfFloor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(roomStandard, house.roomStandard) && Objects.equals(getServiceName(), house.getServiceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomStandard, super.getServiceName());
    }
}
