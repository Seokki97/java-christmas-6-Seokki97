package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    private Discount discount;

    @BeforeEach
    void set(){
        Map<EventList, Integer> map = new HashMap<>();

        map.put(EventList.GIFT,25000);
        map.put(EventList.SUNDAY,2023);
        discount = new Discount(map);
    }
    @DisplayName("혜택 금액 총합을 계산한다.")
    @Test
    void calculateTotalBenefitMoney(){

        Assertions.assertEquals(27023,discount.getTotalBenefitMoney());
    }

    @DisplayName("Gift 항목을 제외한 혜택금액의 총합을 계산한다")
    @Test
    void calculateBenefitsExceptGift(){
        Assertions.assertEquals(2023,discount.calculateTotalDiscountMoneyExceptGift());
    }
}
