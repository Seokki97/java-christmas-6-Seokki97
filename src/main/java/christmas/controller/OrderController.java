package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
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
    private final OutputView outputView;

    private final InputValidator inputValidator;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputValidator = new InputValidator();
    }

    public int readVisitDay() {
        inputView.showVisitDay();
        String inputData = Console.readLine();
        inputValidator.validateInputDataIsNumber(inputData);
        int date = Integer.parseInt(inputData);
        inputValidator.validateInputDataInDateRange(date);
        return date;
    }

    public List<String> readOrderMenuInformation() {
        inputView.showOrderMenu();
        String orderList = Console.readLine();
        String[] menuInformation = orderList.split(",");
        return Arrays.stream(menuInformation).toList();
    }

    public List<OrderRequest> generateOrderRequest(List<String> orderList) {
        List<OrderRequest> orderRequests = new ArrayList<>();
        for (String menu : orderList) {
            String[] menuInformation = menu.split("-");
            String menuName = menuInformation[0];
            int orderCount = Integer.parseInt(menuInformation[1]);
            orderRequests.add(new OrderRequest(menuName, orderCount));
        }
        return orderRequests;
    }

}
