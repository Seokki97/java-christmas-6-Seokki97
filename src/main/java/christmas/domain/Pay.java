package christmas.domain;

import java.util.List;

public class Pay {

    private final int totalPrice;
    private final int discountedPrice;

    public Pay(List<Order> orderList, DisCount disCount) {
        this.totalPrice = calculateTotalPrice(orderList);
        this.discountedPrice = getDiscountedPrice(disCount);
    }

    public int calculateTotalPrice(List<Order> orderList) {
        int sum = 0;
        for (Order order : orderList) {
            sum += order.calculatePrice();
        }
        return sum;
    }

    public int getDiscountedPrice(DisCount disCount) {
        if (disCount.hasGiftEvent()) {
            return totalPrice + disCount.getTotalDiscountPrice() + 25000;
        }
        return totalPrice + disCount.getTotalDiscountPrice();
    }

    public boolean isTotalPayOverGiftEventPrice() {
        return totalPrice > 120000;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
