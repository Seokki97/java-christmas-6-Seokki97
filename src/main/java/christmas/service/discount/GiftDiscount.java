package christmas.service.discount;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import christmas.domain.Pay;
import java.util.Map;

public class GiftDiscount implements DiscountStrategy {

    private final Pay pay;

    public GiftDiscount(Pay pay) {
        this.pay = pay;
    }

    @Override
    public void calculateDiscount(Map<EventList, Integer> discountList) {
        if (pay.isTotalPayOverEventPrice()) {
            discountList.put(EventList.GIFT,
                    discountList.getOrDefault(EventList.GIFT, DiscountPrice.GIFT.getDiscountPrice()));
        }
    }

}
