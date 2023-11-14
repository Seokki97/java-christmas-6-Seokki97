package christmas.domain;


public class Pay {

    private static final int EVENT_PRICE = 120000;
    private static final int EVENT_APPLYING_CONDITION = 10000;

    private final int totalPrice;

    public Pay(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String checkCanGetGift() {
        if (isTotalPayOverEventPrice()) {
            return EventList.CHAMPAGNE.getEventName();
        }
        return EventList.NOTHING.getEventName();
    }

    public boolean isTotalPayOverEventPrice() {
        return totalPrice >= EVENT_PRICE;
    }

    public boolean isEventApplyingCondition() {
        return totalPrice >= EVENT_APPLYING_CONDITION;
    }

    public int calculatePayAmountAfterDisCount(Discount discount) {
        return totalPrice - discount.calculateTotalDiscountMoneyExceptGift();
    }
    
    public int getTotalPay() {
        return totalPrice;
    }
}
