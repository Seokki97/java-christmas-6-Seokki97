package christmas.controller;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import christmas.domain.OrderList;
import christmas.domain.Pay;
import christmas.service.CalendarService;
import christmas.service.discount.DayDiscountStrategy;
import christmas.service.discount.GiftDiscountStrategy;
import christmas.service.discount.StarDiscountStrategy;
import christmas.service.discount.WeekendDiscountStrategy;
import java.util.HashMap;
import java.util.Map;

public class EventController {

    private final DayDiscountStrategy dayDiscountStrategy;
    private final GiftDiscountStrategy giftDiscountStrategy;
    private final StarDiscountStrategy starDiscountStrategy;
    private final WeekendDiscountStrategy weekendDiscountStrategy;

    public EventController(OrderList orderList, int visitDay, Pay pay) {
        final CalendarService calendarService = new CalendarService();
        this.dayDiscountStrategy = new DayDiscountStrategy(visitDay);
        this.giftDiscountStrategy = new GiftDiscountStrategy(pay);
        this.starDiscountStrategy = new StarDiscountStrategy(calendarService, visitDay);
        this.weekendDiscountStrategy = new WeekendDiscountStrategy(calendarService, orderList, visitDay);
    }
    public Map<EventList, Integer> calculateDayOfWeekDiscount(Pay pay) {
        Map<EventList, Integer> discountList = new HashMap<>();
        if (!pay.isEventCondition()) {
            discountList.put(EventList.NOTHING,
                    discountList.getOrDefault(EventList.NOTHING, DiscountPrice.DEFAULT.getDiscountPrice()));
            return discountList;
        }
        weekendDiscountStrategy.calculateDiscount(discountList);
        starDiscountStrategy.calculateDiscount(discountList);
        giftDiscountStrategy.calculateDiscount(discountList);
        dayDiscountStrategy.calculateDiscount(discountList);
        return discountList;
    }
}
