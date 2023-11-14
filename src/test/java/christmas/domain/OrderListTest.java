package christmas.domain;

import christmas.dto.OrderRequest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderListTest {

    private OrderList orderList;

    @BeforeEach
    void setUp(){
        orderList = new OrderList(new ArrayList<>());
        List<OrderRequest> orderRequestList = List.of(
                new OrderRequest("제로콜라", 2),
                new OrderRequest("초코케이크", 1));
        orderList = orderList.addMenuInOrderList(orderRequestList);
    }

    @DisplayName("주문한 메뉴를 OrderList에 추가한다.")
    @Test
    void addMenuInOrder() {

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, orderList.orderList().size()),
                () -> Assertions.assertEquals(2, orderList.orderList().get(0).orderCount()),
                () -> Assertions.assertEquals("제로콜라", orderList.orderList().get(0).orderItem().getName())
        );
    }
}
