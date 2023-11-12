package christmas.domain;

import java.util.Collections;
import java.util.Map;

public class Discount {

    private final Map<EventList, Integer> discountList;

    public Discount(Map<EventList, Integer> discountList) {
        this.discountList = discountList;
    }

    public void removeGiftElement() {
        discountList.remove(EventList.GIFT);
    }

    public Map<EventList, Integer> getDiscountList() {
        return Collections.unmodifiableMap(discountList);
    }

    public int calculateTotalDiscountPrice() {
        return discountList.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Badge findBadge() {
        int totalDiscountPrice = calculateTotalDiscountPrice();
        if (totalDiscountPrice > Badge.SANTA.getMoney()) {
            return Badge.SANTA;
        }
        if (totalDiscountPrice > Badge.TREE.getMoney()) {
            return Badge.TREE;
        }
        if (totalDiscountPrice > Badge.STAR.getMoney()) {
            return Badge.STAR;
        }
        return Badge.NOTHING;
    }
}
