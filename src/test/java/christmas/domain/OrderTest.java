package christmas.domain;

import christmas.repository.MenuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @DisplayName("주문한 음식의 수량을 바탕으로 가격을 계산한다.")
    @Test
    void calculatePrice() {
        Order order = new Order(MenuRepository.BBQ, 3);

        int price = order.calculatePrice();

        Assertions.assertEquals(162000, price);
    }

}
