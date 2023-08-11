package Salon.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private String name;
    private List<ServiceAvailability> services;

    public Employee(String name, List<ServiceAvailability> services) {
        this.name = name;
        this.services = services;
    }
    public Employee(String name, ServiceAvailability service) {
        this.name = name;
        this.services = new ArrayList<>();
        services.add(service);
    }

    public String getName() {
        return name;
    }

    public List<ServiceAvailability> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return "Employee{" + "Name: " + name + "  ; Services: " + services + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    public void addServices(List<ServiceAvailability> newElement){
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
}
