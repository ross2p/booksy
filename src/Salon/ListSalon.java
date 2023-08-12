package Salon;

import Salon.Class.Days;
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
import java.util.*;

public class ListSalon {
    static List<Salon> list = new ArrayList<>();

    ListSalon() {
    }

    ListSalon(String fileName) {
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

    public static List<Salon> searchByNameSalon(String name) {
        List<Salon> foundSalons = new ArrayList<>();
        for (Salon s : list) {
            if (s.getName().toUpperCase().contains(name.toUpperCase())) {
                foundSalons.add(s);
            }
        }
        return foundSalons;
    }

    public static void searchAndMakeReservation(String name) {
        List<Salon> foundSalons = searchByNameSalon(name);

        if (foundSalons.isEmpty()) {
            System.out.println("\u001B[31m" + "No salons found with the given name." + "\u001B[0m");
            return;
        }
        System.out.println("Found " + foundSalons.size() + " salons:");
        for (int i = 0; i < foundSalons.size(); i++) {
            System.out.println((i + 1) + ". " + foundSalons.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to make a reservation? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter the index of the salon you want to make a reservation at: ");
            int index = scanner.nextInt();

            if (index >= 0 && index < foundSalons.size()) {
                Salon selectedSalon = foundSalons.get(index - 1);
                makeReservation(selectedSalon);
            } else {
                System.out.println("\u001B[31m" + "Invalid index." + "\u001B[0m");
            }
        }
    }

    private static void makeReservation(Salon salon) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masters found:");
        List<Employee> employees = salon.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getName());
        }
        System.out.print("Enter the master number: ");
        int employeeIndex = scanner.nextInt();

        if (employeeIndex >= 1 && employeeIndex <= employees.size()) {
            Employee selectedEmployee = employees.get(employeeIndex - 1);

            System.out.println("Select service:");
            List<ServiceAvailability> services = selectedEmployee.getServices();
            for (int i = 0; i < services.size(); i++) {
                System.out.println((i + 1) + ". " + services.get(i).getServiceName());
            }
            System.out.print("Enter the service number: ");
            int serviceIndex = scanner.nextInt();

            if (serviceIndex >= 1 && serviceIndex <= services.size()) {
                ServiceAvailability selectedService = services.get(serviceIndex - 1);

                System.out.print("Enter day : ");
                String dayInput = scanner.next().toUpperCase();
                try {
                    Days day = Days.valueOf(dayInput);
                    Map<String, Boolean> availableHours = selectedService.getHoursAvailability().get(day);
                    System.out.println("Available hours for " + (selectedEmployee.getName()) + " on " + day + ":");
                    for (Map.Entry<String, Boolean> entry : availableHours.entrySet()) {
                        if (entry.getValue()) {
                            System.out.println("\u001B[32m" + entry.getKey() + "\u001B[0m");
                        }
                    }
                    System.out.print("Enter hour: ");
                    String hour = scanner.next();

                    if (availableHours.containsKey(hour) && availableHours.get(hour)) {
                        availableHours.put(hour, false);
                        System.out.println("+-------------+--------------------+");
                        System.out.println("|              𝗥𝗘𝗖𝗢𝗥𝗗              |");
                        System.out.println("+-------------+--------------------+");
                        System.out.println("|  Salon name |" + String.format("%-20s", salon.getName()) + "|");
                        System.out.println("|  Address    |" + String.format("%-20s", salon.getAddress()) + "|");
                        System.out.println("|  Service    |" + String.format("%-20s", selectedService.getServiceName()) + "|");
                        System.out.println("|  Master     |" + String.format("%-20s", selectedEmployee.getName()) + "|");
                        System.out.println("|  Day        |" + String.format("%-20s", day) + "|");
                        System.out.println("|  Time       |" + String.format("%-20s", hour) + "|");
                        System.out.println("+--------------+-------------------+");
                    } else {
                        System.out.println("\u001B[32m" + "It is not possible to sign up at this hour. Please choose another hour." + "\u001B[0m");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid day input.");
                }
            } else {
                System.out.println("Invalid service index.");
            }
        } else {
            System.out.println("Invalid employee index.");
        }
    }

    public static List<Salon> searchByService(String serviceName) {
        List<Salon> foundSalons = new ArrayList<>();
        for (Salon s : list) {
            List<Employee> employeeList = s.getEmployees();
            for (Employee e : employeeList) {
                List<ServiceAvailability> services = e.getServices();
                for (ServiceAvailability ser : services) {
                    if (ser.getServiceName().toUpperCase().contains(serviceName.toUpperCase())) {
                        Employee temp = new Employee(e.getName(), ser);
                        foundSalons.add(new Salon(s.getName(), temp, s.getAddress(), s.getWorkingDays()));
                    }
                }
            }
        }
        return foundSalons;
    }

    public static void searchByServiceAndMakeReservation(String serviceName) {
        List<Salon> foundSalons = searchByService(serviceName);

        if (foundSalons.isEmpty()) {
            System.out.println("\u001B[31m" + "No salons found offering the requested service." + "\u001B[0m");
            return;
        }

        System.out.println("Found " + foundSalons.size() + " salons offering the requested service:");
        for (int i = 0; i < foundSalons.size(); i++) {
            System.out.println((i + 1) + ". " + foundSalons.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you want to make a reservation? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter the index of the salon you want to make a reservation at: ");
            int index = scanner.nextInt();
            index--;

            if (index >= 0 && index < foundSalons.size()) {
                Salon selectedSalon = foundSalons.get(index);
                makeReservation(selectedSalon);
            } else {
                System.out.println("\u001B[31m" + "Invalid index." + "\u001B[0m");
            }
        }
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
}
