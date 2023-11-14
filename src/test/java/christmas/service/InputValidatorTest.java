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
}
