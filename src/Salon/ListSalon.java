package Salon;

import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.ServiceAvailability;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListSalon {
    List<Salon> list = new ArrayList<>();

    ListSalon(){}

    ListSalon(String fileName){
        Gson gson = new Gson();
        List<Salon> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Salon/salon.json"))) {
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
    public void add(Salon newElement){
        //Перевірка
        boolean isRepeated = false;
        for (Salon s: list){
            if (this.equals(newElement)) {
                isRepeated = true;
                s.addEmployees(newElement);
            }
        }
        if (!isRepeated){
            list.add(newElement);
        }
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
           List<Employee> employeeList = s.getEmployees();
           for (Employee e: employeeList){
               List<ServiceAvailability> services = e.getServices();
               for (ServiceAvailability ser: services){
                   if (ser.getServiceName().toUpperCase().contains(name.toUpperCase())){
                       Employee temp = new Employee(e.getName(),ser);
                       listByServices.add(new Salon(s.getName(),temp, s.getAddress(),s.getWorkingDays()));
                   }
               }
           }
        }

        return listByServices;
    }
}
