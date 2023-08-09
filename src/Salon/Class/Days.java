package Salon.Class;

public enum Days {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

//    public static Days getDay(String s){
//
//        return switch (s.toUpperCase()){
//            case "TUESDAY" -> TUESDAY;
//            case "WEDNESDAY" -> WEDNESDAY;
//            case "THURSDAY" -> THURSDAY;
//            case "FRIDAY" -> FRIDAY;
//            case "SATURDAY" -> SATURDAY;
//            case "SUNDAY" -> SUNDAY;
//            default -> MONDAY;
//        };
//    }
public static Days getDay(String s) {
    try {
        return Days.valueOf(s.toUpperCase());
    } catch (IllegalArgumentException e) {
        return Days.MONDAY;
    }
}
}
