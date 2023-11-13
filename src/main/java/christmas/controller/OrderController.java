package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.dto.OrderRequest;
import christmas.service.InputValidator;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderController {

    private final InputView inputView;
    private final InputValidator inputValidator;
    private final OrderService orderService;
    private final OutputView outputView;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.inputValidator = new InputValidator();
        this.orderService = new OrderService();
        this.outputView = outputView;
    }

    public int readVisitDay() {
        inputView.showVisitDay();
        int date;
        while (true) {
            try {
                date = inputValidator.readVisitDay(Console.readLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    public List<OrderRequest> readOrderMenuInformation() {
        inputView.showOrderMenu();
        List<OrderRequest> orderRequests;
        while (true) {
            try {
                List<String> orderMenu = validateOrderMenuInformation();
                orderRequests = generateOrderRequest(orderMenu);
                inputValidator.validateOrderMenuIsDuplicate(orderRequests);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderRequests;
    }

    public List<Order> readOrderMenu() {
        List<OrderRequest> orderRequests = readOrderMenuInformation();
        List<Order> orders = orderService.generateOrderList(orderRequests);
        outputView.showOrderMenu(orders);
        return orders;
    }

    private List<String> validateOrderMenuInformation() {
        String menuInformation = Console.readLine();
        inputValidator.checkMenuDataIsWrong(menuInformation);
        return Arrays.stream(menuInformation.split(",")).toList();
    }

    private List<OrderRequest> generateOrderRequest(List<String> orderList) {
        List<OrderRequest> orderRequests = new ArrayList<>();
        for (String order : orderList) {
            String[] menuInformation = inputValidator.checkNameAndCount(order);
            String menuName = menuInformation[0];
            int orderCount = Integer.parseInt(menuInformation[1]);
            orderRequests.add(new OrderRequest(menuName, orderCount));
        }
        return orderRequests;
    }
}
