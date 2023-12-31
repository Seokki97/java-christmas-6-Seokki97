package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.dto.OrderRequest;
import christmas.repository.MenuRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String NUMBER = "^[0-9]+$";
    private static final String DASH = "-";
    private static final String BLANK = "";
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final String DRINK = "음료";
    private static final int MENU = 0;
    private static final int ORDER_COUNT = 1;

    private static final int LAST_DAY = 31;
    private static final int START_DAY = 1;

    public int validateVisitDay(String day) {
        validateDayIsNumber(day);
        int date = Integer.parseInt(day);
        validateInputDataInDateRange(date);
        return date;
    }

    private void validateDayIsNumber(String inputData) {
        if (!Pattern.matches(NUMBER, inputData)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateInputDataInDateRange(int date) {
        if (date > LAST_DAY || date < START_DAY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public void checkMenuInformationIsWrong(String orderMenu) {
        validateOrderMenuContainHyphen(orderMenu);
        validateOrderMenuContainBlank(orderMenu);
        validateOneMenuHasCommaDelimiter(orderMenu);
    }

    private void validateOrderMenuContainBlank(String orderMenu) {
        if (orderMenu.contains(SPACE)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOrderMenuContainHyphen(String orderMenu) {
        if (!orderMenu.contains(DASH)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOneMenuHasCommaDelimiter(String orderMenu) {
        if (orderMenu.contains(COMMA) && orderMenu.split(COMMA).length == 1) {
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

    public String[] checkMenuInformation(String orderItem) {
        String[] menuInformation = orderItem.split("-");
        String menuName = menuInformation[MENU];
        checkOrderItemInMenuList(menuName);
        validateMenuCountIsNotNumber(menuInformation[ORDER_COUNT]);
        return menuInformation;
    }

    private void validateMenuCountIsNotNumber(String inputData) {
        if (!Pattern.matches(NUMBER, inputData)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void checkOrderItemInMenuList(String orderItem) {
        MenuRepository.findMenuByOrderItem(orderItem);
    }

    public void checkOrderMenuOnlyDrinkType(OrderList orderList) {
        if (isOrderMenuOnlyDrinkType(orderList)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isOrderMenuOnlyDrinkType(OrderList orderList) {
        return orderList.orderList().stream().map(Order::orderItem)
                .map(MenuRepository::getType)
                .allMatch(x -> x.equals(DRINK));
    }
}
