package christmas.repository;

import java.util.List;

public enum MenuRepository {

    SOUP("애피타이저", "양송이수프", 6000),
    TAPASE("애피타이저", "타파스", 5500),
    SALAD("애피타이저", "시저샐러드", 8000),
    STAKE("메인", "티본스테이크", 55000),
    BBQ("메인", "바비큐립", 54000),
    SEA_PASTA("메인", "해산물파스타", 35000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25000),
    CHOCO_CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    ZERO_COKE("음료", "제로콜라", 3000),
    RED_WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000);

    private final String menuType;
    private final String name;
    private final int price;

    MenuRepository(String menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static MenuRepository findMenuByOrderItem(String orderItem) {
        List<MenuRepository> menuList = List.of(MenuRepository.values());

        return menuList.stream()
                .filter(item -> item.name.equals(orderItem))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    public boolean isSameType(String type) {
        return menuType.equals(type);
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return menuType;
    }
}
