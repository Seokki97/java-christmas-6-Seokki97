package christmas.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public class Discount {

    private final Map<EventList, Integer> discountList;

    public Discount(Map<EventList, Integer> discountList) {
        this.discountList = discountList;
    }

    public void removeGiftElement() {
        discountList.remove(EventList.GIFT);
    }

}
