package christmas.service;

import christmas.dto.OrderRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String NUMBER = "^[0-9]+$";

    public void validateInputDataIsNumber(String inputData) {
        if (!Pattern.matches(NUMBER, inputData)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateInputDataInDateRange(int date) {
        if (date > 31 || date < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
    public void validateInputtedOrderData(String orderMenu){
        validateOrderMenuIsOverZero(orderMenu);
        validateOrderMenuContainBlank(orderMenu);
        validateOrderMenuContainHyphen(orderMenu);
    }
    private void validateOrderMenuIsOverZero(String orderMenu) {
        if (!orderMenu.contains(",")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOrderMenuContainBlank(String orderMenu) {
        if (orderMenu.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOrderMenuContainHyphen(String orderMenu) {
        if (!orderMenu.contains("-")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void validateOrderMenuIsDuplicate(List<OrderRequest> orderList) {
        List<String> menuNames = orderList.stream().map(OrderRequest::menu).toList();
        Set<String> copyMenu = new HashSet<>(menuNames);
        if (menuNames.size() != copyMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
