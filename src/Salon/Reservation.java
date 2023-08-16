package Salon;

import Salon.Class.Days;
import Salon.Class.Employee;
import Salon.Class.Salon;
import Salon.Class.ServiceAvailability;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

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
        return "+-------------+--------------------+\n" +
                "|              𝗥𝗘𝗖𝗢𝗥𝗗              |\n" +
                "+-------------+--------------------+\n" +
                "|  Salon name |" + String.format("%-20s", salon.getName()) + "|\n" +
                "|  Address    |" + String.format("%-20s", salon.getAddress()) + "|\n" +
                "|  Service    |" + String.format("%-20s", serviceAvailability.getServiceName()) + "|\n" +
                "|  Master     |" + String.format("%-20s", employee.getName()) + "|\n" +
                "|  Day        |" + String.format("%-20s", day) + "|\n" +
                "|  Time       |" + String.format("%-20s", hours) + "|\n" +
                "+-------------+--------------------+\n";
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

                            reservationTablesToString.append(printReservation(s, e, ser, newDay, hour));
                        }
                    }
                }
            }
        }

        return reservationTablesToString.toString();
    }
    static public String timeToRecord(Days day, String hours) {

        StringBuilder buffer = new StringBuilder();

        DayOfWeek selectedDay = Days.getDayOfWeekFromDay(day);
        String selectedTime = hours;

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime selectedDateTime = today
                .with(selectedDay)
                .withHour(Integer.parseInt(selectedTime.split(":")[0]))
                .withMinute(Integer.parseInt(selectedTime.split(":")[1]))
                .withSecond(0);

        if (selectedDateTime.isBefore(today)) {
            selectedDateTime = selectedDateTime.plusWeeks(1);
        }

        Duration durationDifference = Duration.between(today, selectedDateTime);

        long daysDifference = durationDifference.toDays();
        long hoursDifference = durationDifference.toHoursPart();
        long minutesDifference = durationDifference.toMinutesPart();

        buffer.append(daysDifference).append("d ");
        buffer.append(hoursDifference).append("h ");
        buffer.append(minutesDifference).append("m");

        return buffer.toString();
    }

    public List<Salon> getReservationTables() {
        return reservationTables;
    }

    public int size() {
        return reservationTables.size();
    }

    public void deleteAllReservations() {
        reservationTables.clear();
    }
}