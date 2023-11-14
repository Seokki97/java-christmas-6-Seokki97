package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<EventList, Integer> discountList;
    private final int totalDiscountMoney;

    public Discount(Map<EventList, Integer> discountList) {
        this.discountList = discountList;
        this.totalDiscountMoney = calculateTotalDiscountMoney();
    }

    public int calculateTotalDiscountMoney() {
        return discountList.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateTotalDiscountMoneyExceptGift(){
        Map<EventList,Integer> copyDiscountList = new HashMap<>(Map.copyOf(discountList));
        copyDiscountList.remove(EventList.GIFT);
        return copyDiscountList.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<EventList, Integer> discountList() {
        return Collections.unmodifiableMap(discountList);
    }


    public Badge findBadge() {
        if (totalDiscountMoney > Badge.SANTA.getMoney()) {
            return Badge.SANTA;
        }
        if (totalDiscountMoney > Badge.TREE.getMoney()) {
            return Badge.TREE;
        }
        if (totalDiscountMoney > Badge.STAR.getMoney()) {
            return Badge.STAR;
        }
        return Badge.NOTHING;
    }

    public int getTotalDiscountMoney() {
        return totalDiscountMoney;
    }
}
