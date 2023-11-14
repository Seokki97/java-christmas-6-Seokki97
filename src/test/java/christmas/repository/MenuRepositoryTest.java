package christmas.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuRepositoryTest {

    @DisplayName("메뉴 타입이 같은지 검증한다.")
    @Test
    void checkMenuType(){
        MenuRepository menuItem = MenuRepository.CHAMPAGNE;

        Assertions.assertAll(
                () -> Assertions.assertTrue(menuItem.isSameType("음료")),
                () -> Assertions.assertFalse(menuItem.isSameType("디저트"))
        );
    }

    @DisplayName("주문한 메뉴를 찾는다. 없을시 예외를 발생시킨다.")
    @Test
    void findMenu(){
        MenuRepository coke = MenuRepository.findMenuByOrderItem("제로콜라");

        Assertions.assertAll(
                () -> Assertions.assertEquals("제로콜라",coke.getName()),
                () -> Assertions.assertThrows(IllegalArgumentException.class,() -> MenuRepository.findMenuByOrderItem("모라"))
        );
    }
}
