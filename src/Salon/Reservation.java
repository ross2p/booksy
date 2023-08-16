package Salon;

import Salon.Class.Salon;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private List<Salon> reservationTables = new ArrayList<>();



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