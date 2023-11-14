package christmas.service.discount;

import christmas.domain.DiscountPrice;
import christmas.domain.EventList;
import christmas.domain.Pay;
import christmas.service.discount.DiscountStrategy;
import java.util.Map;

public class GiftDiscountStrategy implements DiscountStrategy {

    private final Pay pay;

    public GiftDiscountStrategy(Pay pay) {
        this.pay = pay;
    }

    @Override
    public void calculateDiscount(Map<EventList, Integer> discountList) {
        if (pay.isTotalPayOverGiftEventPrice()) {
            discountList.put(EventList.GIFT,
                    discountList.getOrDefault(EventList.GIFT, DiscountPrice.GIFT.getDiscountPrice()));
        }
    }

}
