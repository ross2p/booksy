package Salon.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Salon.ListSalon;
public class Reservation {
    public String makeReservation2(Salon salon, Employee employee, ServiceAvailability serviceAvailability, Days day, String hours, ListSalon list) {
        List<Salon> updatedSalons = new ArrayList<>(list.getList());
        StringBuilder output = new StringBuilder();

        for (Salon s : updatedSalons) {
            if (s.equals(salon)) {
                for (Employee e : s.getEmployees()) {
                    if (e.equals(employee)) {
                        for (ServiceAvailability serAva : e.getServices()) {
                            if (serAva.equals(serviceAvailability)) {
                                serAva.getHoursAvailability().get(day).replace(hours, false);

                                output.append("+-------------+--------------------+\n");
                                output.append("|              𝗥𝗘𝗖𝗢𝗥𝗗              |\n");
                                output.append("+-------------+--------------------+\n");
                                output.append("|  Salon name |").append(String.format("%-20s", salon.getName())).append("|\n");
                                output.append("|  Address    |").append(String.format("%-20s", s.getAddress())).append("|\n");
                                output.append("|  Service    |").append(String.format("%-20s", serviceAvailability.getServiceName())).append("|\n");
                                output.append("|  Master     |").append(String.format("%-20s", employee.getName())).append("|\n");
                                output.append("|  Day        |").append(String.format("%-20s", day)).append("|\n");
                                output.append("|  Time       |").append(String.format("%-20s", hours)).append("|\n");
                                output.append("+--------------+-------------------+\n");

                                break;
                            }
                        }
                    }
                }
            }
        }
        return output.toString();
    }
}