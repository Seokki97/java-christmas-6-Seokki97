package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Pay;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;
    private final OrderService orderService;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = new OrderService();
    }

    public List<String> readOrderMenuInformation() {
        String data = inputView.readOrderMenu();
        String[] menuInformation = data.split(",");
        return Arrays.stream(menuInformation).toList();
    }

    public List<Order> receiveOrder() {
        List<Order> orderList = orderService.generateOrderList(readOrderMenuInformation());
        outputView.showOrderMenu(orderList);
        return orderList;
    }
}
