package Salon.Class;

import java.util.*;

public class Employee {
    private String name;
    private Set<ServiceAvailability> services;         //щоб не було однакових послуг

    public Employee(String name, Set<ServiceAvailability> services) {
        this.name = name;
        this.services = services;
    }
    public Employee(String name, ServiceAvailability service) {
        this.name = name;
        this.services = new HashSet<>();
        services.add(service);
    }

    public String getName() {
        return name;
    }

    public Set<ServiceAvailability> getServices() {
        return services;
    }

    public String toString() {
        StringBuilder servicesToStr = new StringBuilder();
        for (ServiceAvailability s : services) {
            servicesToStr.append("\n\t\t").append(s);
        }
        return name + servicesToStr.toString();
    }


    @Override
    //По назві
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    public void addServices(Set<ServiceAvailability> newElement){
        for (ServiceAvailability newServices: newElement){
            boolean isRepeated = false;
            for (ServiceAvailability s:services){
                if ( s.equals(newServices)) {
                    isRepeated = true;
                    s.addHoursAvailability(newServices.getHoursAvailability());
                }
            }
            if (!isRepeated){
                services.add(newServices);
            }
        }
    }
    public ServiceAvailability getService(String servicesName){
        for (ServiceAvailability ser: services){
            if (ser.getServiceName().equals(servicesName)){
                return ser;
            }
        }
        return null;
    }
}
