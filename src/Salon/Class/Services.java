package Salon.Class;

import java.util.Map;

public class Services {
    String name;
    Map<Days, Map<String, Boolean>> reservation;

    public Services(String name, Map<Days, Map<String, Boolean>> reservation) {
        this.name = name;
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return name + "\n\t" + reservation;
    }
}
