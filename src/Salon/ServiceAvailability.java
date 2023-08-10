package Salon;

import java.util.Map;

public class ServiceAvailability {
    private String serviceName;
    private Map<String, Boolean> hoursAvailability;

    public ServiceAvailability(String serviceName, Map<String, Boolean> hoursAvailability) {
        this.serviceName = serviceName;
        this.hoursAvailability = hoursAvailability;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Map<String, Boolean> getHoursAvailability() {
        return hoursAvailability;
    }
}
