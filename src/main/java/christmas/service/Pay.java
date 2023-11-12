package christmas.service;


import christmas.domain.EventList;

public class Pay {

    private static final int EVENT_PRICE = 120000;
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
        return totalPrice > 10000;
    }

    public int getTotalPay() {
        return totalPrice;
    }
}
