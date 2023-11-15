package christmas.service;

import christmas.domain.EventList;
import christmas.domain.Pay;
import christmas.service.discount.GiftDiscount;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftBenefitsTest {

    @DisplayName("증정 할인이 적용되는지 검증한다.")
    @Test
    void validateGiftEvent(){
        GiftDiscount giftDiscount = new GiftDiscount(new Pay(120000));
        Map<EventList, Integer> discountList = new HashMap<>();
        giftDiscount.calculateDiscount(discountList);
        Assertions.assertAll(
                () -> Assertions.assertEquals(discountList.get(EventList.GIFT),25000)
        );
    }
}
