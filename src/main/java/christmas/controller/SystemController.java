package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Benefits;
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
        Pay pay = new Pay(orderList.calculateTotalPrice());
        EventController eventController = new EventController(orderList, visitDay, pay);
        Map<EventList, Integer> discountList = eventController.applyAllEvent(pay);
        Benefits benefits = new Benefits(discountList);
        outputView.showResult(pay, benefits);
        showDiscountPrice(benefits, pay);
    }

    private void showDiscountPrice(Benefits benefits, Pay pay) {
        Badge badge = benefits.findBadge();
        int totalDiscountPayPrice = pay.calculatePayAmountAfterDisCount(benefits);
        outputView.showAfterDiscountPrice(totalDiscountPayPrice);
        outputView.showEventBadge(badge);
    }
}
