package christmas.service;


public class Pay {

    private static final int EVENT_PRICE = 120000;
    private final int totalPrice;

    public Pay(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isTotalPayOverGiftEventPrice() {
        return totalPrice > EVENT_PRICE;
    }

    public int getTotalPay() {
        return totalPrice;
    }
}
