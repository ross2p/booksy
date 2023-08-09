package Salon;

import Salon.Class.Days;
import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /*для тесту


        Map<Days, Map<String, Boolean>> reservation = new HashMap<>();
        reservation.put(Days.MONDAY, new HashMap<>());
        reservation.get(Days.MONDAY).put("8:20", true);
        reservation.get(Days.MONDAY).put("8:40", false);

        reservation.put(Days.WEDNESDAY, new HashMap<>());
        reservation.get(Days.WEDNESDAY).put("8:20", true);

        List<Services> servicesList = new ArrayList<>();
        servicesList.add(new Services("Haircut", reservation));
        servicesList.add(new Services("Manicure", reservation));
        Map<Days, String> hoursOfWork = new HashMap<>();
        hoursOfWork.put(Days.MONDAY, "8:20-8:40");
        hoursOfWork.put(Days.WEDNESDAY, "8:20-8:40");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Oleg","Ivanov",servicesList));
        employeeList.add(new Employee("Olga","Ivanivna",servicesList));
        Salon s = new Salon("name","address", employeeList, hoursOfWork);

        ListSalon list = new ListSalon();
        list.add(s);
        System.out.println(list);
        System.out.println("---------------");
        System.out.println(list.searchByNameServices("Hair"));
*/
    }
}
