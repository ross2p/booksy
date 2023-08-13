package Salon;

import Salon.Class.*;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String menu = """
            [1] Print all Salon
            [2] Search by salon name
            [3] Search by service name
            [4] Sorted by name of Salon
            [5] Make a reservation
            [6] Withdrawal of all reservations
            [0] Finish
            """;

    public static void main(String[] args) {
        Start.start();
        ListSalon list = new ListSalon("src/Salon/salon.json");
        int choice;
        Reservation reservation = new Reservation();
        do {
            System.out.println(menu);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(list);
                    break;

                case 2:
                    System.out.print("Search: ");
                    String searchNameSalon = sc.nextLine();
                    System.out.println(list.searchByNameSalon(searchNameSalon));
                    break;

                case 3:
                    System.out.print("Search: ");
                    String searchNameServices = sc.nextLine();
                    System.out.println(searchNameServices);
                    System.out.println(list.searchByService(searchNameServices));

                    break;

                case 4:
                    System.out.print("All salons sorted: \n");
                    list.sortBySalonName();
                    System.out.println(list);
                    break;
                case 5:
                    String salonName, employeeName, service, day, hour;

                    System.out.print("Salon name: ");
                    salonName = sc.nextLine();

                    Salon temp = list.getSalon(salonName);

                    if (temp != null) {
                        System.out.println(temp.getEmployees());
                        System.out.print("Employee name: ");
                        employeeName = sc.nextLine();

                        Employee selectedEmployee = temp.getEmployee(employeeName);

                        if (selectedEmployee != null) {
                            System.out.println("\n" + selectedEmployee.printServicesName());
                            System.out.print("Service: ");
                            service = sc.nextLine();

                            ServiceAvailability selectedService = selectedEmployee.getService(service);

                            if (selectedService != null) {
                                System.out.println(selectedService.printDays());
                                System.out.print("Day: ");
                                day = sc.nextLine();

                                Days selectedDay = Days.getDay(day);

                                System.out.println(selectedService.printHours(selectedDay));
                                System.out.print("Hours: ");
                                hour = sc.nextLine();

                                boolean reservationSuccess = list.makeReservation(salonName, employeeName, service, day, hour);

                                if (reservationSuccess) {
                                    String reservationOutput = reservation.makeReservation2(temp, selectedEmployee, selectedService, selectedDay, hour);
                                    System.out.println(reservationOutput);
                                } else {
                                    System.out.println("Reservation failed.");
                                }
                            } else {
                                System.out.println("Service not found.");
                            }
                        } else {
                            System.out.println("Employee not found.");
                        }
                    } else {
                        System.out.println("Salon not found.");
                    }


                    System.out.println();
                    break;

                case 6:
                    System.out.println("All your records : ");
                    String allReservationsOutput = reservation.printAllReservations();
                    if (allReservationsOutput.isEmpty()) {
                        System.out.println("\u001B[31m"+"You don't have any records yet\n"+"\u001B[0m");
                    } else {
                        System.out.print("\u001B[32m"+allReservationsOutput+"\u001B[0m");
                    }
                    break;
                default:
                    choice = 0;
                    break;

            }
        } while (choice != 0);
        sc.close();
    }
}