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
        StringBuilder reservationToStr = new StringBuilder();

        for (Map.Entry<Days, Map<String, Boolean>> entry : reservation.entrySet()) {
            Days day = entry.getKey();
            Map<String, Boolean> roomMap = entry.getValue();
            reservationToStr.append("\n\t\t\t").append(day).append("\n\t\t\t\t");

            for (Map.Entry<String, Boolean> hourEntry : roomMap.entrySet()) {
                String hour = hourEntry.getKey();
                Boolean isReserved = hourEntry.getValue();
                reservationToStr.append("[").append(hour).append(": ").append(Boolean.toString(isReserved)).append("]");
            }
        }
        return name + reservationToStr.toString();
    }

    public String getName() {
        return name;
    }
}
