package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderRequest;
import christmas.service.InputValidator;
import christmas.view.InputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderController {

    private final InputView inputView;

    private final InputValidator inputValidator;

    public OrderController(InputView inputView) {
        this.inputView = inputView;
        this.inputValidator = new InputValidator();
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

    public List<OrderRequest> validateOrderMenuInformation() {
        inputView.showOrderMenu();
        List<OrderRequest> orderRequests;
        while (true) {
            try {
                List<String> orderMenu = readOrderMenuInformation();
                orderRequests = generateOrderRequest(orderMenu);
                inputValidator.validateOrderMenuIsDuplicate(orderRequests);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderRequests;
    }

    public List<String> readOrderMenuInformation() {
        String menuInformation = Console.readLine();
        inputValidator.checkMenuDataIsWrong(menuInformation);
        return Arrays.stream(menuInformation.split(",")).toList();
    }

    public List<OrderRequest> generateOrderRequest(List<String> orderList) {
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
