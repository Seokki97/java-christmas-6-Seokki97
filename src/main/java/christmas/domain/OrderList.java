package christmas.domain;

import christmas.dto.OrderRequest;
import christmas.repository.MenuRepository;
import java.util.Collections;
import java.util.List;

public record OrderList(List<Order> orderList) {

    private static final int ZERO = 0;

    public OrderList addMenuInOrderList(List<OrderRequest> orderRequests) {
        for (OrderRequest orderRequest : orderRequests) {
            MenuRepository item = MenuRepository.findMenuByOrderItem(orderRequest.menu());
            orderList.add(new Order(item, orderRequest.orderCount()));
        }
        return new OrderList(orderList);
    }

    public int calculateTotalPrice() {
        int sum = ZERO;
        for (Order order : orderList) {
            sum += order.calculatePrice();
        }
        return sum;
    }

    @Override
    public List<Order> orderList() {
        return Collections.unmodifiableList(orderList);
    }
}
