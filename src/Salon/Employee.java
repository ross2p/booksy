package Salon;

import java.util.List;
public class Employee {
    private String name;
    private List<ServiceAvailability> services;

    public Employee(String name, List<ServiceAvailability> services) {
        this.name = name;
        this.services = services;
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
}
