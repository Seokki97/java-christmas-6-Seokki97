package christmas.service.discount;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import java.util.Map;

public class DayDiscount implements DiscountStrategy {

    private final int visitDay;

    public DayDiscount(int visitDay) {
        this.visitDay = visitDay;
    }

    @Override
    public void calculateDiscount(Map<EventList, Integer> discountList) {
        int basicDiscount = DiscountPrice.DAY.getDiscountPrice();
        for (int i = 1; i < visitDay; i++) {
            basicDiscount += DiscountPrice.EVERYDAY.getDiscountPrice();
        }
        discountList.put(EventList.D_DAY,
                discountList.getOrDefault(EventList.D_DAY, basicDiscount));
    }
}
