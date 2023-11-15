package christmas.service;

import christmas.domain.EventList;
import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.repository.MenuRepository;
import christmas.service.discount.WeekendDiscount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class WeekendBenefitsTest {

    @DisplayName("주말일경우 메인메뉴, 평일일경우 디저트 메뉴만 할인을 적용한다.")
    @Test
    void calculateWeekendEvent(){
        List<Order> orders = List.of(
                new Order(MenuRepository.RED_WINE,1),
                new Order(MenuRepository.ZERO_COKE,2),
                new Order(MenuRepository.SEA_PASTA,3),
                new Order(MenuRepository.CHOCO_CAKE,1)
        );
        OrderList orderList = new OrderList(orders);
        WeekendDiscount holiday = new WeekendDiscount(new CalendarService(),orderList,2);
        Map<EventList, Integer> discountList = new HashMap<>();
        holiday.calculateDiscount(discountList);
        WeekendDiscount weekDay = new WeekendDiscount(new CalendarService(),orderList,5);
        weekDay.calculateDiscount(discountList);
        Assertions.assertAll(
                () -> Assertions.assertEquals(discountList.get(EventList.SUNDAY),6069),
                () -> Assertions.assertEquals(discountList.get(EventList.WEEKDAY),2023)
        );
    }
}
