package christmas.service.discount;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import christmas.service.CalendarService;
import java.util.Map;

public class StarDiscount implements DiscountStrategy {

    private final CalendarService calendarService;
    private final int visitDay;

    public StarDiscount(CalendarService calendarService, int visitDay) {
        this.calendarService = calendarService;
        this.visitDay = visitDay;
    }

    @Override
    public void calculateDiscount(Map<EventList, Integer> discountList) {
        if (calendarService.isStarDay(visitDay)) {
            discountList.put(EventList.SPECIAL,
                    discountList.getOrDefault(EventList.SPECIAL, DiscountPrice.DEFAULT.getDiscountPrice())
                            + DiscountPrice.SPECIAL.getDiscountPrice());
        }
    }
}
