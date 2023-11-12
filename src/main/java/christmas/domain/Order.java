package christmas.domain;

import christmas.repository.MenuRepository;

public record Order(MenuRepository orderItem, int orderCount) {

    @Override
    public String toString() {
        return orderItem.getName() + " " + orderCount + "개";
    }

    public int calculatePrice() {
        return orderItem.getPrice() * orderCount;
    }
}
