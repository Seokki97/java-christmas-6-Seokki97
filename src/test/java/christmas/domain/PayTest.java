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

    @DisplayName("증정 선물을 받는 지 검증한다")
    @Test
    void canGetEvent() {
        Pay gift = new Pay(130000);
        Pay nonGift = new Pay(10000);
        Assertions.assertAll(
                () -> Assertions.assertEquals("샴페인 1개", gift.checkCanGetGift()),
                () -> Assertions.assertEquals("없음", nonGift.checkCanGetGift())
        );
    }

    @DisplayName("구매 금액이 이벤트 적용되는 금액인지 확인한다.")
    @Test
    void isApplyEvent(){
        Pay eventFalse = new Pay(3000);
        Pay eventTrue = new Pay(12000);
        Assertions.assertAll(
                () -> Assertions.assertFalse(eventFalse.isEventCondition()),
                () -> Assertions.assertTrue(eventTrue.isEventCondition())
        );
    }
}
