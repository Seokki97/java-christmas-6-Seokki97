package christmas.service.discount;

import christmas.domain.EventList;
import java.util.Map;

public interface DiscountStrategy {

    void calculateDiscount(Map<EventList, Integer> discountList);
}
