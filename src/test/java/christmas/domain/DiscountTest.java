package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    private Discount discount;

    @DisplayName("혜택 금액 총합을 계산한다.")
    @Test
    void calculateTotalBenefitMoney(){
        Map<EventList, Integer> map = new HashMap<>();

        map.put(EventList.GIFT,25000);
        map.put(EventList.SUNDAY,2023);
        discount = new Discount(map);

        Assertions.assertEquals(27023,discount.getTotalBenefitMoney());
    }
}
