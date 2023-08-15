package Salon.Class;

public enum Days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Days getDay(String s) {
        try {
            return Days.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Days.MONDAY;
        }
    }
}