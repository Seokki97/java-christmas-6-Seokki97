package christmas.domain;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Event {

    private Set<EventList> eventList;

    public Event(Set<EventList> eventList) {
        this.eventList = eventList;
    }

    public int findDayOfWeek(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public Set<EventList> findEvent(int day, Pay pay) {
        eventList.add(EventList.isStarEvent(day));
        eventList.add(EventList.isGiftEvent(pay));
        eventList.add(EventList.findDayEvent(findDayOfWeek(day)));
        if (eventList.size() > 1) {
            eventList.remove(EventList.NOTHING);
        }
        return eventList;
    }
}
