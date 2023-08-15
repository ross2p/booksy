package Salon;

import Salon.Class.Days;
import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.ServiceAvailability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reservation {
    private List<Salon> reservationTables = new ArrayList<>();

    public String makeReservation(Salon salon, Employee employee, ServiceAvailability serviceAvailability, Days day, String hours) {
        Map<String, Boolean> hoursMap = new HashMap<>();
        hoursMap.put(hours, false);
        Map<Days, Map<String, Boolean>> mapDays = new HashMap<>();
        mapDays.put(day, hoursMap);
        add(new Salon(salon.getName(), new Employee(employee.getName(), new ServiceAvailability(serviceAvailability.getServiceName(), mapDays)), salon.getAddress(), salon.getWorkingDays()));
        return printReservation(salon, employee, serviceAvailability, day, hours);
    }

    static public String printReservation(Salon salon, Employee employee, ServiceAvailability serviceAvailability, Days day, String hours) {
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
        return output.toString();
    }

    public void add(Salon newElement) {
        boolean isRepeated = false;
        for (Salon s : reservationTables) {
            if (s.equals(newElement)) {
                isRepeated = true;
                s.addEmployees(newElement.getEmployees());
            }
        }
        if (!isRepeated) {
            reservationTables.add(newElement);
        }
    }

    @Override
    public String toString() {
        StringBuilder reservationTablesToString = new StringBuilder();

        for (Salon s : reservationTables) {
            for (Employee e : s.getEmployees()) {
                for (ServiceAvailability ser : e.getServices()) {

                    for (Map.Entry<Days, Map<String, Boolean>> newEntry : ser.getHoursAvailability().entrySet()) {
                        Days newDay = newEntry.getKey();
                        Map<String, Boolean> hoursMap = newEntry.getValue();
                        for (Map.Entry<String, Boolean> hoursMapEntry : hoursMap.entrySet()) {
                            String hour = hoursMapEntry.getKey();
                            Boolean isReservation = hoursMapEntry.getValue();
                            reservationTablesToString.append(printReservation(s, e, ser, newDay, hour));
                        }
                    }
                }
            }
        }

        return reservationTablesToString.toString();
    }

    public List<Salon> getReservationTables() {
        return reservationTables;
    }

    public void deleteAllReservations() {
        reservationTables.clear();
    }
}