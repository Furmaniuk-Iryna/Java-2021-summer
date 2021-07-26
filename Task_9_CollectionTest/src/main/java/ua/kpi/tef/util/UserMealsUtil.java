package ua.kpi.tef.util;

import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.Month.MAY;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, MAY, 30, 14, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, MAY, 30, 19, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, MAY, 31, 20, 0), "Ужин", 510)
        );


   List<UserMealWithExceed> userMealWithExceedList =  getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(userMealWithExceedList);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> sumCaloriesByDay = mealList.stream().collect(
                Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> userMealWithExceed = mealList.stream()
                .filter(meal -> TimeUtil.isBetween(LocalTime.from(meal.getDateTime()), startTime, endTime))
                .collect(Collectors.mapping(meal -> {
                    return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), ExceedUtil.exceedValue(sumCaloriesByDay.get(meal.getDateTime().toLocalDate()), caloriesPerDay));
                }, Collectors.toList()));

        return userMealWithExceed;
    }
}
