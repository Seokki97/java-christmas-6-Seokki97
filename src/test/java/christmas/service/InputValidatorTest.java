package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.dto.OrderRequest;
import christmas.repository.MenuRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @DisplayName("날짜 입력이 잘못됐을 경우 예외를 발생시킨다.")
    @Test
    void validateVisitDay() {
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.validateVisitDay("32")),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.validateVisitDay("a")),
                () -> Assertions.assertDoesNotThrow(() -> inputValidator.validateVisitDay("2"))
        );
    }

    @DisplayName("메뉴 이름과 주문 갯수를 검증한다.")
    @Test
    void checkNameAndCount() {
        String nameError = "햄버거-1";
        String countError = "제로콜라-a";
        String notError = "제로콜라-1";
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuInformation(nameError)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuInformation(countError)),
                () -> Assertions.assertDoesNotThrow(() -> inputValidator.checkMenuInformation(notError))
        );
    }

    @DisplayName("메뉴 입력에 대한 오류를 검증한다.")
    @Test
    void checkMenuInput(){
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuInformationIsWrong("제로콜라 1")),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuInformationIsWrong("제로콜라-1 ")),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuInformationIsWrong("제로콜라-1,")),
                () -> Assertions.assertDoesNotThrow(() -> inputValidator.checkMenuInformationIsWrong("제로콜라-1"))
        );
    }

    @DisplayName("주문한 음식이 중복되었는지 검증한다.")
    @Test
    void checkMenuDuplication(){
        List<OrderRequest> orderRequest = List.of(
                new OrderRequest("제로콜라",1),
                new OrderRequest("제로콜라",2)
        );
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.validateOrderMenuIsDuplicate(orderRequest))
        );
    }

    @DisplayName("주문한 음식이 음료타입만 있을 경우 예외를 발생시킨다.")
    @Test
    void checkIsOnlyDrink(){
        List<Order> orders = List.of(
                new Order(MenuRepository.RED_WINE,1),
                new Order(MenuRepository.ZERO_COKE,2)
        );
        OrderList orderList = new OrderList(orders);

        Assertions.assertAll(
                ()-> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkOrderMenuOnlyDrinkType(orderList))
        );
    }
}
