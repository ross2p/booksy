package Salon.Class;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    FullName fullName;
    List<Services> services;
    public class FullName {
        private String firstName;
        private String lastName;

        public FullName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        @Override
        public String toString() {
            return "Full Name: "+ firstName + " " + lastName;
        }
    }

    public Employee(FullName fullName, List<Services> services) {
        this.fullName = fullName;
        this.services = services;
    }
    public Employee(String firstName, String lastName, List<Services> services){
        fullName = new FullName(firstName, lastName);
        this.services = services;
    }
    public Employee(FullName fullName, Services services) {
        this.fullName =fullName;
        this.services = new ArrayList<>();
        this.services.add(services);
    }

    @Override
    public String toString() {
        StringBuilder servicesToStr = new StringBuilder();
        for (Services s : services) {
            servicesToStr.append("\n\t\t").append(s);
        }
        return fullName + servicesToStr.toString();
    }
    public FullName getFullName() {
        return fullName;
    }

    public List<Services> getServices() {
        return services;
    }
}
