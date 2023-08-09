package Salon.Class;

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

    @Override
    public String toString() {
        return fullName + "\n" + services;
    }
}
