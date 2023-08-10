package Salon;

import java.util.List;

public class Salon {
    final String name;
    List<Employee> employees;
    final String address;
    final List<Days> workingDays;
    private static int counter = 0;

    public Salon(String name, List<Employee> employees,String address, List<Days> workingDays) {
        this.name = name;
        this.employees = employees;
        this.address = address;
        this.workingDays = workingDays;
    }
    public String getName(){
        return name;
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public String getAddress(){
        return address;
    }

    @Override
    public String toString() {
        counter++;
        return "Salon number " + counter + " {" + " Name: " + name + "  ;\nEmployees: " + employees + "  ;\nAddress: "
                + address + "\nWorkingDays: " + workingDays + " }\n\n";
    }
}