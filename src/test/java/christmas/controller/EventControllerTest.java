package christmas.controller;

import christmas.domain.EventList;
import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.domain.Pay;
import christmas.repository.MenuRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventControllerTest {

    @DisplayName("이벤트 적용 조건에 부합하면 모든 이벤트를 적용한다.")
    @Test
    void applyEvent() {
        List<Order> orderList = List.of(
                new Order(MenuRepository.CHOCO_CAKE, 3),
                new Order(MenuRepository.ZERO_COKE, 5),
                new Order(MenuRepository.SEA_PASTA,3),
                new Order(MenuRepository.SALAD,3)
        );
        OrderList order = new OrderList(orderList);
        int totalPrice = order.calculateTotalPrice();
        Pay pay = new Pay(totalPrice);
        EventController eventOn = new EventController(order, 13, pay);

        Map<EventList, Integer> applyingEvent = eventOn.applyAllEvent(pay);

        Map<EventList, Integer> notApplyingEvent = eventOn.applyAllEvent(new Pay(0));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, notApplyingEvent.size()),
                () -> Assertions.assertEquals(notApplyingEvent.get(EventList.NOTHING), 0),
                () -> Assertions.assertEquals(3, applyingEvent.size())
        );
    }
}
