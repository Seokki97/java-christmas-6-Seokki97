package christmas.domain;

import christmas.repository.MenuRepository;

public record Order(MenuRepository orderItem, int orderCount) {

    public int calculatePrice() {
        return orderItem.getPrice() * orderCount;
    }

    @Override
    public String toString() {
        return orderItem.getName() + " " + orderCount + "개";
    }
}
