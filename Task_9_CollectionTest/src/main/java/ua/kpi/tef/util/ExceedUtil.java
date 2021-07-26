package ua.kpi.tef.util;

public class ExceedUtil {
    static boolean exceed;

    public static boolean exceedValue(int calories, int caloriesPerDay) {
        exceed = calories > caloriesPerDay;
        return exceed;
    }
}
