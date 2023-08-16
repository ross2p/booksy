package Salon;

import Salon.Class.Days;
import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.ServiceAvailability;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListSalon {
    private List<Salon> list;

    public ListSalon() {
        list = new ArrayList<>();
    }


    public void add(Salon newElement) {
        boolean isRepeated = false;
        for (Salon s : list) {
            if (s.equals(newElement)) {
                isRepeated = true;
                s.addEmployees(newElement.getEmployees());
            }
        }
        if (!isRepeated) {
            list.add(newElement);
        }
    }

    @Override
    public String toString() {
        StringBuilder listToStr = new StringBuilder();
        for (Salon s : list) {
            listToStr.append(s.toString());
        }
        return listToStr.toString();
    }

    public ListSalon searchByNameSalon(String name) {
        ListSalon listByName = new ListSalon();
        for (Salon s : list) {
            if (s.getName().toUpperCase().contains(name.toUpperCase())) {
                listByName.add(s);
            }
        }
        return listByName;
    }

    public boolean makeReservation(Salon salon, Employee employee, ServiceAvailability serviceAvailability, Days day, String hours) {
        for (Salon s : list) {

            if (s.equals(salon)) {

                for (Employee e : s.getEmployees()) {
                    if (e.equals(employee)) {

                        for (ServiceAvailability serAva : e.getServices()) {

                            if (serAva.equals(serviceAvailability)) {
                                if (serAva.getHoursAvailability().get(day).get(hours)) {
                                    serAva.getHoursAvailability().get(day).replace(hours, false);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public ListSalon searchByService(String serviceName) {
        ListSalon foundSalons = new ListSalon();
        for (Salon s : list) {
            for (Employee e : s.getEmployees()) {
                for (ServiceAvailability ser : e.getServices()) {
                    if (ser.getServiceName().toUpperCase().contains(serviceName.toUpperCase())) {
                        Employee temp = new Employee(e.getName(), ser);
                        foundSalons.add(new Salon(s.getName(), temp, s.getAddress(), s.getWorkingDays()));
                    }
                }
            }
        }
        return foundSalons;
    }

    public void sortBySalonName() {
        list.sort(new SortBySalonName());
    }


    static private class SortBySalonName implements Comparator<Salon> {
        @Override
        public int compare(Salon s1, Salon s2) {
            return s1.getName().compareTo(s2.getName());
        }
    }

    public List<Salon> getList() {
        return list;
    }

    public Salon getSalon(String name) {
        for (Salon s : list) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
