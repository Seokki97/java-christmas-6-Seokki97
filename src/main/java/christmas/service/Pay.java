package christmas.service;


import christmas.domain.Discount;
import christmas.domain.EventList;

public class Pay {

    private static final int EVENT_PRICE = 120000;
    private static final int EVENT_APPLYING_CONDITION = 10000;
    private final int totalPrice;

    public Pay(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String validateCanGetGift() {
        if (isTotalPayOverGiftEventPrice()) {
            return EventList.CHAMPAGNE.getEventName();
        }
        return EventList.NOTHING.getEventName();
    }

    public boolean isTotalPayOverGiftEventPrice() {
        return totalPrice > EVENT_PRICE;
    }

    public boolean isEventCondition() {
        return totalPrice > EVENT_APPLYING_CONDITION;
    }

    public int getTotalPay() {
        return totalPrice;
    }

    public int getPayMoneyAfterDisCount(Discount discount) {
        discount.removeGiftElement();
        return totalPrice - discount.calculateTotalDiscountPrice();
    }
}
