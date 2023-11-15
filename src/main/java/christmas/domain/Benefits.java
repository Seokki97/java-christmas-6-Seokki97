package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Benefits {

    private final Map<EventList, Integer> discountList;
    private final int totalBenefitMoney;

    public Benefits(Map<EventList, Integer> discountList) {
        this.discountList = discountList;
        this.totalBenefitMoney = calculateTotalBenefitMoney();
    }

    private int calculateTotalBenefitMoney() {
        return discountList.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateTotalBenefitMoneyExceptGift() {
        Map<EventList, Integer> copyDiscountList = new HashMap<>(Map.copyOf(discountList));
        copyDiscountList.remove(EventList.GIFT);
        return copyDiscountList.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<EventList, Integer> discountList() {
        return Collections.unmodifiableMap(discountList);
    }

    public Badge findBadge() {
        if (totalBenefitMoney > Badge.SANTA.getMoney()) {
            return Badge.SANTA;
        }
        if (totalBenefitMoney > Badge.TREE.getMoney()) {
            return Badge.TREE;
        }
        if (totalBenefitMoney > Badge.STAR.getMoney()) {
            return Badge.STAR;
        }
        return Badge.NOTHING;
    }

    public int getTotalBenefitMoney() {
        return totalBenefitMoney;
    }
}
