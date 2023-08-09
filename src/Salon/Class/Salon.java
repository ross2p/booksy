package Salon.Class;

import java.util.ArrayList;
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
    public Salon(String name, String address, Employee employee, Map<Days, String> hoursOfWork) {
        this.name = name;
        this.address = address;
        this.employee = new ArrayList<>();
        this.employee.add(employee);
        HoursOfWork = hoursOfWork;
    }

    @Override
    public String toString() {
        String HoursOfWorkToStr = "";

        for (Map.Entry<Days, String> entry : HoursOfWork.entrySet()) {
            HoursOfWorkToStr +="\n\t" +entry.getKey() + "\t" + entry.getValue();
        }

        String employeeToStr = "";
        for (Employee e: employee){
            employeeToStr +="\n\t" +  e.toString();
        }

        return "Name: " +name+'\n'+
                "Address: " + address +  '\n' +
                "HoursOfWork: " + HoursOfWorkToStr + '\n' +
                "Employee: "+ employeeToStr;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public Map<Days, String> getHoursOfWork() {
        return HoursOfWork;
    }

}
