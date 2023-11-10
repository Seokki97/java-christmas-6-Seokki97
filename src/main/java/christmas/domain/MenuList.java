package christmas.domain;

public enum MenuList {

    SOUP("양송이수프", 6000),
    TAPASE("타파스", 5500),
    SALAD("시저샐러드", 8000),
    STAKE("티본스테이크", 55000),
    BBQ("바비큐립", 54000),
    SEA_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCO_CAKE("초코케이트", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);
    private final String name;
    private final int price;

    MenuList(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static MenuList findMenuList(String menu) {
        return MenuList.valueOf(menu);
    }
}
