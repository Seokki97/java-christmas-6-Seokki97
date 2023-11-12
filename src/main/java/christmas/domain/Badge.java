package christmas.domain;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음", 0);

    private final String name;
    private final int money;

    Badge(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public static Badge findBadge(Discount disCount) {
        if (disCount.calculateTotalDiscountPrice() > SANTA.money) {
            return SANTA;
        }
        if (disCount.calculateTotalDiscountPrice() > TREE.money) {
            return TREE;
        }
        if (disCount.calculateTotalDiscountPrice() > STAR.money) {
            return STAR;
        }
        return NOTHING;
    }

    public String getBadgeName() {
        return name;
    }
}
