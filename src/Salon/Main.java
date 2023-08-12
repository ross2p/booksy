package Salon;

import Salon.Class.Salon;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String menu = """
            1. Print all Salon
            2. Search by salon name
            3. Search by service name
            4. Sorted by name of Salon
            5. Make a reservation
            0. Finish
            """;
    public static void main(String[] args) {
        Start.start();
        ListSalon list = new ListSalon("src/Salon/salon.json");
        int choice;
        do {
            System.out.println(menu);
            choice = sc.nextInt();
            System.out.println(choice);
            switch (choice) {
                case 1:
                    System.out.println(list);
                    break;

                case 2:

                    sc.nextLine();
                    System.out.print("Search: ");
                    String searchNameSalon = sc.nextLine();
                    System.out.println(list.searchByNameSalon(searchNameSalon));
                    break;

                case 3: {
                    sc.nextLine();
                    System.out.print("Search: ");
                    String searchNameServices = sc.nextLine();
                    System.out.println(searchNameServices);
                    System.out.println(list.searchByService(searchNameServices));

                    break;
                }
                case 4:
                    System.out.print("All salons sorted: \n");
                    list.sortBySalonName();
                    System.out.println(list);
                    break;
                case 5:
                    System.out.println(list);
                    String salonName, employeeName,service, day, hour;

                    System.out.print("Salon name: ");
                    salonName = sc.nextLine();

                    Salon temp = list.getSalon(salonName);
                    System.out.println(temp.getEmployees());
                    System.out.print("Employee name: ");
                    employeeName = sc.nextLine();

                    System.out.println(temp.getEmployee(employeeName).getServices());
                    System.out.print("Service: ");
                    service = sc.nextLine();

                    System.out.println(temp.getEmployee(employeeName).getService(service).getHoursAvailability());
                    System.out.print("Day: ");
                    day = sc.nextLine();

                    System.out.println(temp.getEmployee(employeeName).getService(service).getHoursInDay(day));
                    System.out.print("Hours: ");
                    hour = sc.nextLine();

                    list.makeReservation(salonName,employeeName,service,day,hour);
                default:
                    choice = 0;
                    break;

            }
        }while(choice != 0);
        sc.close();
    }
}