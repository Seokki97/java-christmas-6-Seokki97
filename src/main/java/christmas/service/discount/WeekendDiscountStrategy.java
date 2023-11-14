package christmas.service.discount;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.service.CalendarService;
import java.util.Map;

public class WeekendDiscountStrategy implements DiscountStrategy {

    private static final String MAIN_TYPE = "메인";
    private static final String DESSERT_TYPE = "디저트";
    private final CalendarService calendarService;
    private final OrderList orderList;
    private final int visitedDay;

    public WeekendDiscountStrategy(CalendarService calendarService, OrderList orderList, int visitedDay) {
        this.calendarService = calendarService;
        this.orderList = orderList;
        this.visitedDay = visitedDay;
    }

    @Override
    public void calculateDiscount(Map<EventList, Integer> discountList) {
        int dayOfWeek = calendarService.findVisitDayOfWeek(visitedDay);
            if (calendarService.isWeekend(dayOfWeek)) {
                for (Order order : orderList.orderList()) {
                    findSameMenuType(discountList, MAIN_TYPE, order);
                }
                return;
            }
            for (Order order : orderList.orderList()) {
                findSameMenuType(discountList, DESSERT_TYPE, order);
            }
    }

    private void findSameMenuType(Map<EventList, Integer> discountList, String menuType,
                                  Order order) {
        if (order.orderItem().isSameType(menuType)) {
            discountList.put(EventList.SUNDAY,
                    discountList.getOrDefault(EventList.SUNDAY, DiscountPrice.DEFAULT.getDiscountPrice())
                            + DiscountPrice.MENU.getDiscountPrice() * order.orderCount());
        }
    }
}