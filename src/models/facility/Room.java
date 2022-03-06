package models.facility;

import java.util.Objects;

public class Room extends Facility {
    private String freeService;

    public Room() {
    }

    public Room(String codeService, String serviceName, double areaUsable, int rent, int maxPeople, String rentType, String freeService) {
        super(codeService, serviceName, areaUsable, rent, maxPeople, rentType);
        this.freeService = freeService;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    @Override
    public String toString() {
        return "Room {" +
                super.toString() +
                ",freeService'" + freeService +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(this.getServiceName(), room.getServiceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getServiceName());
    }
}
