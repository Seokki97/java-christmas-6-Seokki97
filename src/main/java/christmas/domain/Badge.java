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

    public static Badge findBadge(DisCount disCount) {
        if (-disCount.getTotalDiscountPrice() > SANTA.money) {
            return SANTA;
        }
        if (-disCount.getTotalDiscountPrice() > TREE.money) {
            return TREE;
        }
        if (-disCount.getTotalDiscountPrice() > STAR.money) {
            return STAR;
        }
        return NOTHING;
    }

    public String getBadgeName() {
        return name;
    }
}
