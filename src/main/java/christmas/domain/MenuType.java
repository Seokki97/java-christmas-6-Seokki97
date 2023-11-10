package christmas.domain;

public enum MenuType {

    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료"),
    NONE("해당없음");

    private final String type;

    MenuType(String type) {
        this.type = type;
    }

    public static MenuType findMenuType(String menuType) {
        return MenuType.valueOf(menuType);
    }
}
