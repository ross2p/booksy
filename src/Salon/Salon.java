package Salon;

import java.util.List;

enum Days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class Salon {
    final String name;
    List<String> employees;
    final String address;
    final List<Days> workingDays;
    private static int counter = 0;

    public Salon(String name, List<String> employees, String address, List<Days> workingDays) {
        this.name = name;
        this.employees = employees;
        this.address = address;
        this.workingDays = workingDays;
    }

    @Override
    public String toString() {
        counter++;
        return "Salon number " + counter +" {" + " Name: " + name + "  ; Employees: " + employees + "  ; Address: "
                + address + "  ; WorkingDays: " + workingDays + " }";
    }
}