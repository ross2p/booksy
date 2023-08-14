package Salon.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Salon.ListSalon;

public class Reservation {
    private List<String> reservationTables = new ArrayList<>();
    public String makeReservation2(Salon salon, Employee employee, ServiceAvailability serviceAvailability, Days day, String hours) {
        StringBuilder output = new StringBuilder();

        output.append("+-------------+--------------------+\n");
        output.append("|              𝗥𝗘𝗖𝗢𝗥𝗗              |\n");
        output.append("+-------------+--------------------+\n");
        output.append("|  Salon name |").append(String.format("%-20s", salon.getName())).append("|\n");
        output.append("|  Address    |").append(String.format("%-20s", salon.getAddress())).append("|\n");
        output.append("|  Service    |").append(String.format("%-20s", serviceAvailability.getServiceName())).append("|\n");
        output.append("|  Master     |").append(String.format("%-20s", employee.getName())).append("|\n");
        output.append("|  Day        |").append(String.format("%-20s", day)).append("|\n");
        output.append("|  Time       |").append(String.format("%-20s", hours)).append("|\n");
        output.append("+-------------+--------------------+\n");
        String table = output.toString();
        reservationTables.add(table);

        return table;
    }
    public String printAllReservations() {
        StringBuilder allTablesOutput = new StringBuilder();

        for (String table : reservationTables) {
            allTablesOutput.append(table);
        }

        return allTablesOutput.toString();
    }
    public void deleteAllReservations() {
        reservationTables.clear();
    }
}