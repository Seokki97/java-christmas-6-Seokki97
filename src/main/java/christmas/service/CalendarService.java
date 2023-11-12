package christmas.service;

import java.util.Calendar;

public class CalendarService {

    public int findVisitDayOfWeek(int visitedDay) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, Calendar.DECEMBER, visitedDay);

        return calendar.get(Calendar.DAY_OF_WEEK); // 6 금 7 토
    }

    public boolean isWeekend(int dayOfWeek) {
        return dayOfWeek >= 6;
    }

    public boolean isStarDay(int visitedDay) {
        return visitedDay % 7 == 3 || visitedDay == 25;
    }
}
