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

    public String getBadgeName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
