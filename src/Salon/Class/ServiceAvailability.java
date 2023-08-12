package Salon.Class;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceAvailability {
    private String serviceName;
    private Map<Days,Map<String, Boolean>> hoursAvailability;

    public ServiceAvailability(String serviceName, Map<Days,Map<String, Boolean>> hoursAvailability) {
        this.serviceName = serviceName;
        this.hoursAvailability = hoursAvailability;
    }

    @Override
    public String toString() {
        StringBuilder reservationToStr = new StringBuilder();

        for (Map.Entry<Days, Map<String, Boolean>> entry : hoursAvailability.entrySet()) {
            Days day = entry.getKey();
            Map<String, Boolean> roomMap = entry.getValue();
            int temp = 0;
            reservationToStr.append("\n\t\t\t").append(day).append("\n\t\t\t\t");
            for (Map.Entry<String, Boolean> hourEntry : roomMap.entrySet()) {
                String hour = hourEntry.getKey();
                Boolean isReserved = hourEntry.getValue();
                if(temp == 4){
                    reservationToStr.append("\n\t\t\t\t");
                    temp = 0;

                }
                temp++;
                if(isReserved){

                    reservationToStr.append("[").append("\u001B[32m"+(hour)+"\u001B[0m").append("]\t");
                }
                else {
                    reservationToStr.append("[").append("\u001B[31m"+(hour)+"\u001B[0m").append("]\t");
                }
            }
        }
        return serviceName + reservationToStr.toString();
    }

    public String getServiceName() {
        return serviceName;
    }

    public Map<Days,Map<String, Boolean>> getHoursAvailability() {
        return hoursAvailability;
    }

    public void addHoursAvailability(Map<Days, Map<String, Boolean>> newHoursAvailability) {
        for (Map.Entry<Days, Map<String, Boolean>> newEntry : newHoursAvailability.entrySet()) {
            Days newDay = newEntry.getKey();
            Map<String, Boolean> newInnerMap = newEntry.getValue();

            if (hoursAvailability.containsKey(newDay)) {
                Map<String, Boolean> innerMap = hoursAvailability.get(newDay);

                for (Map.Entry<String, Boolean> newInnerEntry : newInnerMap.entrySet()) {
                    String newKey = newInnerEntry.getKey();
                    Boolean newValue = newInnerEntry.getValue();

                    if (!innerMap.containsKey(newKey)) {
                        innerMap.put(newKey, newValue);
                    }
                }
            }
        }
    }
}
