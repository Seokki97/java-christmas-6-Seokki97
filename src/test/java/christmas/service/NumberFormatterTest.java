package christmas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberFormatterTest {

    @DisplayName("금액을 한국 화폐 형식에 맞춰서 반환한다")
    @Test
    void convertMoney(){
        Assertions.assertAll(
                () -> Assertions.assertEquals("1,000", NumberFormatter.formatCurrency(1000)),
                () -> Assertions.assertEquals("10,000", NumberFormatter.formatCurrency(10000))
        );
    }
}
