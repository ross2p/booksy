package Salon;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Start.start();
        Gson gson = new Gson();
        List<Salon> salons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Salon/salon.json"))) {
            Type type = new TypeToken<List<Salon>>() {
            }.getType();
            salons = gson.fromJson(reader, type);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the service you are looking for: ");
            String serviceInput = scanner.nextLine();
            System.out.println("\t\t↓  Our suggestions for " + serviceInput + "\t↓\n");


            for (Salon salon : salons) {
                for (Employee employee : salon.getEmployees()) {
                    for (ServiceAvailability service : employee.getServices()) {
                        if (service.getServiceName().toLowerCase().contains(serviceInput.toLowerCase())) {
                            System.out.println("Salon: " + salon.getName());
                            System.out.println("Employee: " + employee.getName());
                            System.out.println("Address: " + salon.getAddress());

                            System.out.println("Free hours for an appointment for " + service.getServiceName() + ":");
                            int temp = 0;
                            for (Map.Entry<String, Boolean> entry : service.getHoursAvailability().entrySet()) {
                                String hour = entry.getKey();
                                boolean isAvailable = entry.getValue();

                                if (temp == 4) {
                                    System.out.print("\n");
                                    temp = 0;
                                }
                                if (isAvailable) {
                                    temp++;
                                    System.out.print(hour + ",\t");
                                }
                            }
                            System.out.println();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON syntax in the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}