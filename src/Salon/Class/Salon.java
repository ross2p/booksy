package Salon.Class;

import java.util.List;
import java.util.Map;

public class Salon {
    String name;
    String address;
    List<Employee> employee;
    Map<Days, String> HoursOfWork;

    public Salon(String name, String address, List<Employee> employee, Map<Days, String> hoursOfWork) {
        this.name = name;
        this.address = address;
        this.employee = employee;
        HoursOfWork = hoursOfWork;
    }

    @Override
    public String toString() {
        return "Name: " +name+'\n'+ "Address: " + address +  '\n' +"HoursOfWork: " + HoursOfWork + '\n' + employee;
    }
}
