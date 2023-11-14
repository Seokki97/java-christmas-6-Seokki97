package christmas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    /*
    void setInputValidator() {
        inputValidator.checkOrderMenuOnlyDrinkType();
        inputValidator.validateOrderMenuIsDuplicate();
        inputValidator.checkMenuDataIsWrong();
        inputValidator.checkNameAndCount();
        inputValidator.validateVisitDay();
    }*/

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
                        () -> inputValidator.checkNameAndCount(nameError)),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkNameAndCount(countError)),
                () -> Assertions.assertDoesNotThrow(() -> inputValidator.checkNameAndCount(notError))
        );
    }

    @DisplayName("메뉴 입력에 대한 오류를 검증한다.")
    @Test
    void checkMenuInput(){
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuDataIsWrong("제로콜라 1")),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuDataIsWrong("제로콜라-1 ")),
                () -> Assertions.assertThrows(IllegalArgumentException.class,
                        () -> inputValidator.checkMenuDataIsWrong("제로콜라-1,")),
                () -> Assertions.assertDoesNotThrow(() -> inputValidator.checkMenuDataIsWrong("제로콜라-1"))
        );
    }
}
