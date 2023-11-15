package christmas.controller;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import christmas.domain.OrderList;
import christmas.domain.Pay;
import christmas.service.CalendarService;
import christmas.service.discount.DayDiscount;
import christmas.service.discount.GiftDiscount;
import christmas.service.discount.SpecialDiscount;
import christmas.service.discount.WeekendDiscount;
import java.util.HashMap;
import java.util.Map;

public class EventController {

    private final DayDiscount dayDiscountStrategy;
    private final GiftDiscount giftDiscount;
    private final SpecialDiscount specialDiscount;
    private final WeekendDiscount weekendDiscountStrategy;

    public EventController(OrderList orderList, int visitDay, Pay pay) {
        final CalendarService calendarService = new CalendarService();
        this.dayDiscountStrategy = new DayDiscount(visitDay);
        this.giftDiscount = new GiftDiscount(pay);
        this.specialDiscount = new SpecialDiscount(calendarService, visitDay);
        this.weekendDiscountStrategy = new WeekendDiscount(calendarService, orderList, visitDay);
    }

    public Map<EventList, Integer> applyAllEvent(Pay pay) {
        Map<EventList, Integer> discountList = new HashMap<>();
        if (!pay.isEventApplyingCondition()) {
            discountList.put(EventList.NOTHING,
                    discountList.getOrDefault(EventList.NOTHING, DiscountPrice.DEFAULT.getDiscountPrice()));
            return discountList;
        }
        weekendDiscountStrategy.calculateDiscount(discountList);
        specialDiscount.calculateDiscount(discountList);
        giftDiscount.calculateDiscount(discountList);
        dayDiscountStrategy.calculateDiscount(discountList);
        return discountList;
    }
}
