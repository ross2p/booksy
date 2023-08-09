package Salon;

import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.Services;

import java.util.ArrayList;
import java.util.List;

public class ListSalon {
    List<Salon> list = new ArrayList<>();

    ListSalon(){};

    public boolean add(Salon newElement){
        return list.add(newElement);
    }

    @Override
    public String toString() {
        String listToStr = "";
        for (Salon s: list){
            listToStr += s.toString();
        }
        return listToStr;
    }

    public ListSalon searchByNameSalon(String name){
        ListSalon listByName = new ListSalon();
        for (Salon s: list){
           if (s.getName().toUpperCase().contains(name.toUpperCase())){
               listByName.add(s);
           }
        }
        return listByName;
    }
    public ListSalon searchByNameServices(String name){
        ListSalon listByServices = new ListSalon();
        for (Salon s: list){
           List<Employee> employeeList = s.getEmployee();
           for (Employee e: employeeList){
               List<Services> services = e.getServices();
               for (Services ser: services){

                   if (ser.getName().toUpperCase().contains(name.toUpperCase())){
                       Employee temp = new Employee(e.getFullName(),ser);
                       listByServices.add(new Salon(s.getName(),s.getAddress(),temp,s.getHoursOfWork()));
                   }
               }
           }
        }

        return listByServices;
    }
}
