package christmas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PayTest {


    @DisplayName("증정 선물 조건에 부합하는지 검증한다.")
    @Test
    void isEventCondition() {
        Pay gift = new Pay(130000);
        Pay nonGift = new Pay(10000);
        Assertions.assertAll(
                () -> Assertions.assertTrue(gift.isEventCondition()),
                () -> Assertions.assertFalse(nonGift.isEventCondition())
        );
    }
}
