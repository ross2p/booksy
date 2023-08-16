package Salon.Class;

import java.time.DayOfWeek;

public enum Days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Days getDay(String s) {
        try {
            return Days.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Days.MONDAY;
        }
    }

    static public DayOfWeek getDayOfWeekFromDay(Days day) {
        switch (day) {
            case MONDAY:
                return DayOfWeek.MONDAY;
            case TUESDAY:
                return DayOfWeek.TUESDAY;
            case WEDNESDAY:
                return DayOfWeek.WEDNESDAY;
            case THURSDAY:
                return DayOfWeek.THURSDAY;
            case FRIDAY:
                return DayOfWeek.FRIDAY;
            case SATURDAY:
                return DayOfWeek.SATURDAY;
            case SUNDAY:
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Невідомий день: " + day);
        }
    }
}