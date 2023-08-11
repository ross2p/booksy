package Salon.Class;

import java.util.Map;

public class ServiceAvailability {
    private String serviceName;
    private Map<Days,Map<String, Boolean>> hoursAvailability;

    public ServiceAvailability(String serviceName, Map<Days,Map<String, Boolean>> hoursAvailability) {
        this.serviceName = serviceName;
        this.hoursAvailability = hoursAvailability;
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

            for (Map.Entry<Days, Map<String, Boolean>> entry : hoursAvailability.entrySet()) {
                Days day = entry.getKey();
                Map<String, Boolean> innerMap = entry.getValue();

                if (newDay == day) {
                    for (Map.Entry<String, Boolean> newInnerEntry : newInnerMap.entrySet()) {
                        String newKey = newInnerEntry.getKey();
                        Boolean newValue = newInnerEntry.getValue();
                        boolean isRepeated = false;
                        for (Map.Entry<String, Boolean> innerEntry : innerMap.entrySet()) {
                            String key = innerEntry.getKey();
                            Boolean value = innerEntry.getValue();
                            if (key.equals(newKey)) {
                               isRepeated = true;
                               value = newValue;
                            }
                        }
                        if (!isRepeated){
                            innerMap.put(newKey,newValue);
                        }
                    }
                }
            }
        }
    }

}
