package christmas.service;

import christmas.domain.EventList;
import christmas.service.discount.SpecialDiscount;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpecialBenefitsTest {

    @DisplayName("별표시가 된 날짜에 방문했을 시 특별 이벤트를 적용한다. 단, 누적은 안된다.")
    @Test
    void calculateStarEvent(){
        SpecialDiscount sunday = new SpecialDiscount(new CalendarService(),3);
        SpecialDiscount christmas = new SpecialDiscount(new CalendarService(), 25);

        Map<EventList, Integer> discountList = new HashMap<>();
        sunday.calculateDiscount(discountList);
        christmas.calculateDiscount(discountList);
        Assertions.assertAll(
                () -> Assertions.assertEquals(discountList.get(EventList.SPECIAL),1000)
        );
    }
}
