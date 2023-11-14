package christmas.domain;

public enum DiscountPrice {

    DEFAULT(0),
    SPECIAL(1000),
    GIFT(25000),
    MENU(2023),
    DAY(1000),
    EVERYDAY(100);

    private final int discountPrice;

    DiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
