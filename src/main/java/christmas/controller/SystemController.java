package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.EventList;
import christmas.domain.OrderList;
import christmas.domain.Pay;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class SystemController {

    private final OutputView outputView;
    private final OrderController orderController;

    public SystemController(final OutputView outputView) {
        this.outputView = outputView;
        this.orderController = new OrderController(new InputView(), outputView);
    }

    public void run() {
        int visitDay = orderController.readVisitDay();
        OrderList orderList = orderController.readOrderMenuInformation();
        int totalPrice = orderList.calculateTotalPrice();
        Pay pay = new Pay(totalPrice);
        EventController eventController = new EventController(orderList, visitDay, pay);
        Map<EventList, Integer> discountList = eventController.calculateDayOfWeekDiscount(pay);
        Discount discount = new Discount(discountList);
        outputView.showResult(pay, discount);
        showDiscountPrice(discount, pay);
    }

    private void showDiscountPrice(Discount discount, Pay pay) {
        Badge badge = discount.findBadge();
        int totalDiscountPayPrice = pay.calculatePayMoneyAfterDisCount(discount);
        outputView.showAfterDiscountPrice(totalDiscountPayPrice);
        outputView.showEventBadge(badge);
    }
}
