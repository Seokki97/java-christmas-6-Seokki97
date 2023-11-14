package christmas.domain;

import java.util.Collections;
import java.util.Map;

public record Discount(Map<EventList, Integer> discountList) {

    public void removeGiftElement() {
        discountList.remove(EventList.GIFT);
    }

    @Override
    public Map<EventList, Integer> discountList() {
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
