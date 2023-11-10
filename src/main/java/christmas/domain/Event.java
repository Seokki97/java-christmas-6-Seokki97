package christmas.domain;

import java.util.Calendar;

public class Event {

    public boolean isWeekend(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY;
    }
}
