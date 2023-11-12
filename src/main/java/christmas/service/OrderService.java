package christmas.service;

import christmas.dto.OrderRequest;
import christmas.repository.MenuRepository;
import christmas.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final int ZERO = 0;

    public List<Order> generateOrderList(List<OrderRequest> orderRequests) {
        List<Order> orderList = new ArrayList<>();
        for (OrderRequest orderRequest : orderRequests) {
            MenuRepository item = MenuRepository.findMenuByOrderItem(orderRequest.menu());
            orderList.add(new Order(item, orderRequest.orderCount()));
        }
        return orderList;
    }

    public int calculateTotalPrice(List<Order> orderList) {
        int sum = ZERO;
        for (Order order : orderList) {
            sum += order.calculatePrice();
        }
        return sum;
    }
}
