package christmas.service;

import java.util.Calendar;

public class CalendarService {

    private static final int YEAR = 2023;
    private static final int WEEKEND = 6;
    private static final int WEEK_UNIT = 7;
    private static final int STAR_DAY = 3;
    private static final int CHRISTMAS = 25;

    public int findVisitDayOfWeek(int visitedDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, Calendar.DECEMBER, visitedDay);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public boolean isWeekend(int dayOfWeek) {
        return dayOfWeek >= WEEKEND;
    }

    public boolean isStarDay(int visitedDay) {
        return visitedDay % WEEK_UNIT == STAR_DAY || visitedDay == CHRISTMAS;
    }
}
