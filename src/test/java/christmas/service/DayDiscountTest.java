package christmas.service;

import christmas.domain.EventList;
import christmas.service.discount.DayDiscount;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayDiscountTest {

    @DisplayName("디데이 할인이 적용되는지 검증한다.")
    @Test
    void validateDayDisCount(){
        DayDiscount dayDiscount = new DayDiscount(24);
        Map<EventList,Integer> discount = new HashMap<>();

        dayDiscount.calculateDiscount(discount);

        Assertions.assertAll(
                () -> Assertions.assertEquals(discount.get(EventList.D_DAY),3300)
        );
    }
}
