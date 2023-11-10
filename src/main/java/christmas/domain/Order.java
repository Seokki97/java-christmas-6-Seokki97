package christmas.domain;


import christmas.controller.repository.MenuRepository;

public record Order(MenuRepository orderItem, int orderCount) {


    @Override
    public String toString() {
        return orderItem.getName() + " " + orderCount + "개";
    }

}
