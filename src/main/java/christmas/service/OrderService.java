package christmas.service;

import christmas.controller.repository.MenuRepository;
import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<Order> generateOrderList(List<String> menuInformation) {
        List<Order> orderList = new ArrayList<>();

        for (String information : menuInformation) {
            String[] menuItems = information.split("-");
            orderList.add(new Order(MenuRepository.findMenuByOrderItem(menuItems[0]),
                    Integer.parseInt(menuItems[1])));
        }
        return orderList;
    }
}
