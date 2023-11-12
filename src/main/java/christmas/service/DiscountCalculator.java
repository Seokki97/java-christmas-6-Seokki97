package christmas.service;

import christmas.domain.Discount;
import christmas.domain.EventList;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculator {

    private final CalendarService calendarService;

    public DiscountCalculator() {
        this.calendarService = new CalendarService();
    }

    //1일부터 시작하여 1000원으로 시작하여 크리스마스 다가올수록 할인 금액이 100원씩 증가
    public void calculateDayDiscount(int visitDay, Map<EventList, Integer> discountList) {
        int startDiscountMoney = 1000;
        for (int i = 1; i < visitDay; i++) {
            startDiscountMoney += 100;
        }
        discountList.put(EventList.D_DAY,
                discountList.getOrDefault(EventList.D_DAY, startDiscountMoney));
    }

    //방문 날짜가 주말이면? 메인 메뉴를 메뉴 1개당 2023원 할인
    public Map<EventList, Integer> calculateDayOfWeekDiscount(int visitDay, List<Order> orderList, Pay pay) {
        int dayOfWeek = calendarService.findVisitDayOfWeek(visitDay);
        Map<EventList, Integer> discountList = new HashMap<>();
        if (!pay.isEventCondition()) {
            discountList.put(EventList.NOTHING,
                    discountList.getOrDefault(EventList.NOTHING, 0));
            return discountList;
        }
        discountList = calculateWeekendEvent(discountList, orderList, dayOfWeek);
        calculateStarDay(discountList, visitDay);
        calculateCanGetGift(discountList, pay);
        calculateDayDiscount(visitDay, discountList);
        return discountList;
    }

    public void calculateStarDay(Map<EventList, Integer> discountList, int visitDay) {
        if (calendarService.isStarDay(visitDay)) {
            discountList.put(EventList.SPECIAL, discountList.getOrDefault(EventList.SPECIAL, 0) + 1000);
        }
    }

    public void calculateCanGetGift(Map<EventList, Integer> discountList, Pay pay) {
        if (pay.isTotalPayOverGiftEventPrice()) {
            discountList.put(EventList.GIFT, discountList.getOrDefault(EventList.GIFT, 25000));
        }
    }

    public Map<EventList, Integer> calculateWeekendEvent(Map<EventList, Integer> discountList, List<Order> orderList,
                                                         int dayOfWeek) {
        if (calendarService.isWeekend(dayOfWeek)) {
            for (Order order : orderList) {
                findSameMenuType(discountList, "메인", EventList.SUNDAY,order);
            }
            return discountList;
        }
        for (Order order : orderList) {
            findSameMenuType(discountList, "디저트", EventList.SUNDAY,order);
        }
        return discountList;
    }

    public void findSameMenuType(Map<EventList, Integer> discountList, String menuType, EventList eventList,Order order) {
        if (order.orderItem().isSameType(menuType)) {
            discountList.put(eventList,
                    discountList.getOrDefault(eventList, 0) + 2023 * order.orderCount());
        }
    }

    public int calculateDiscountedPayPrice(Discount discount, Pay pay) {
        return pay.getTotalPay() - discount.calculateTotalDiscountPrice();
    }
}
