package Salon.Class;

import java.util.Map;
import java.util.Objects;

public class ServiceAvailability {
    private String serviceName;
    private Map<Days,Map<String, Boolean>> hoursAvailability;

    public ServiceAvailability(String serviceName, Map<Days,Map<String, Boolean>> hoursAvailability) {
        this.serviceName = serviceName;
        this.hoursAvailability = hoursAvailability;
    }

    @Override
    public String toString() {
//        StringBuilder reservationToStr = new StringBuilder();
//
//        for (Map.Entry<Days, Map<String, Boolean>> entry : hoursAvailability.entrySet()) {
//            Days day = entry.getKey();
//            Map<String, Boolean> hoursMap = entry.getValue();
//            int temp = 0;
//            reservationToStr.append("\n\t\t\t").append(day).append("\n\t\t\t\t");
//            for (Map.Entry<String, Boolean> hourEntry : hoursMap.entrySet()) {
//                String hour = hourEntry.getKey();
//                Boolean isReserved = hourEntry.getValue();
//                if(temp == 4){
//                    reservationToStr.append("\n\t\t\t\t");
//                    temp = 0;
//
//                }
//                temp++;
//                if(isReserved){
//
//                    reservationToStr.append("[").append("\u001B[32m"+(hour)+"\u001B[0m").append("]\t");
//                }
//                else {
//                    reservationToStr.append("[").append("\u001B[31m"+(hour)+"\u001B[0m").append("]\t");
//                }
//            }
//        }
        return serviceName /*+ reservationToStr.toString()*/;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Map<Days,Map<String, Boolean>> getHoursAvailability() {
        return hoursAvailability;
    }


    @Override
    // Тільки по Service Name
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceAvailability that)) return false;
        return Objects.equals(serviceName, that.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName);
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
    public String printDays(){
        StringBuilder reservationToStr = new StringBuilder();
        int temp = 0;
        for (Map.Entry<Days, Map<String, Boolean>> entry : hoursAvailability.entrySet()) {
            reservationToStr.append(entry.getKey().toString()).append("\t");
            temp++;
            if (temp == 4){
                reservationToStr.append("\n");
                temp = 0;
            }
        }
        return reservationToStr.toString();
    }
    public String printHours(Days day){
        StringBuilder reservationToStr = new StringBuilder();

        Map<String, Boolean> hoursMap = hoursAvailability.get(day);

            int temp = 0;
            //reservationToStr.append("\n\t\t\t").append(day).append("\n\t\t\t\t");
            for (Map.Entry<String, Boolean> hourEntry : hoursMap.entrySet()) {
                String hour = hourEntry.getKey();
                Boolean isReserved = hourEntry.getValue();
                if(temp == 4){
                    reservationToStr.append("\n");
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

        return reservationToStr.toString();
    }
    public Map<String,Boolean> getHoursInDay(String nameDay){

        for (Map.Entry<Days, Map<String, Boolean>> entry : hoursAvailability.entrySet()) {
            Days day = entry.getKey();
            if (day.equals(Days.getDay(nameDay))){
                Map<String, Boolean> hoursMap = entry.getValue();
                return hoursMap;
            }
        }
        return null;
    }
}
