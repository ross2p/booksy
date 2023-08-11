package Salon.Class;

import java.time.LocalTime;
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
