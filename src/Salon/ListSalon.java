package Salon;

import Salon.Class.Days;
import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.ServiceAvailability;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class ListSalon {
    private List<Salon> list;

    public ListSalon() {
        list = new ArrayList<>();
    }

    public ListSalon(String fileName) {
        list = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Type type = new TypeToken<List<Salon>>() {
            }.getType();
            list = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON syntax in the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Salon newElement) {
        boolean isRepeated = false;
        for (Salon s : list) {
            if (this.equals(newElement)) {
                isRepeated = true;
                s.addEmployees(newElement);
            }
        }
        if (!isRepeated) {
            list.add(newElement);
        }
    }

    @Override
    public String toString() {
        String listToStr = "";
        for (Salon s : list) {
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
    private boolean makeReservation(Salon salon,Employee employee, ServiceAvailability serviceAvailability, Days day, String hours) {
        for (Salon s: list){
            if (s.equals(salon)){
                for (Employee e: s.getEmployees()){
                    if (e.equals(employee)){
                        for (ServiceAvailability serAva: e.getServices()){
                            if (serAva.equals(serviceAvailability)){
                                serAva.getHoursAvailability().get(day).replace(hours,false);
                                return true;    //Успішно змінено
                            }
                        }
                    }
                }
            }
        }
        return false;   //Резервація не завершена
    }
    public boolean makeReservation(String salonName,String employeeName, String serviceAvailabilityName, String dayName, String hours) {
        for (Salon s: list){
            if (s.getName().equals(salonName)){
                for (Employee e: s.getEmployees()){
                    if (e.getName().equals(employeeName)){
                        for (ServiceAvailability serAva: e.getServices()){
                            if (serAva.getServiceName().equals(serviceAvailabilityName)){
                                serAva.getHoursAvailability().get(Days.getDay(dayName)).replace(hours,false);
                                return true;    //Успішно змінено
                            }
                        }
                    }
                }
            }
        }
        return false;   //Резервація не завершена
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
        Collections.sort(list, new SortBySalonName());
    }


    private class SortBySalonName implements Comparator<Salon> {
        @Override
        public int compare(Salon s1, Salon s2) {
            return s1.getName().compareTo(s2.getName());
        }
    }

    public Salon getSalon(String name){
        for (Salon s: list){
            if (s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    public List<Salon> getList() {
        return list;
    }

    public List<String> listSalonString(){
        List<String> listStr = new ArrayList<>();
        for (Salon s: list){
            listStr.add(s.getName()+ "\n"+ s.getAddress());
        }
        return listStr;
    }
}
