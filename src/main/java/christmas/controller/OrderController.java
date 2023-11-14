package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.dto.OrderRequest;
import christmas.service.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderController {

    private final InputView inputView;
    private final InputValidator inputValidator;
    private final OutputView outputView;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.inputValidator = new InputValidator();
        this.outputView = outputView;
    }

    public int readVisitDay() {
        inputView.showVisitDay();
        int date;
        while (true) {
            try {
                date = inputValidator.validateVisitDay(Console.readLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    public OrderList readOrderMenuInformation() {
        inputView.showOrderMenu();
        OrderList orderList;
        while (true) {
            try {
                List<String> orderMenu = validateOrderMenuInformation();
                List<OrderRequest> orderRequests = generateOrderRequest(orderMenu);
                inputValidator.validateOrderMenuIsDuplicate(orderRequests);
                orderList = readOrderMenu(orderRequests);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderList;
    }

    public OrderList readOrderMenu(List<OrderRequest> orderRequests) {
        List<Order> orders = new ArrayList<>();
        OrderList orderList = new OrderList(orders);
        orderList = orderList.addMenuInOrderList(orderRequests);
        inputValidator.checkOrderMenuOnlyDrinkType(orderList);
        outputView.showOrderMenu(orderList);
        return orderList;
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
