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
}
