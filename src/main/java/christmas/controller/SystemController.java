package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.EventList;
import christmas.domain.OrderList;
import christmas.service.DiscountCalculator;
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
        DiscountCalculator discountCalculator = new DiscountCalculator(orderList);
        int totalPrice = orderList.calculateTotalPrice();
        Pay pay = new Pay(totalPrice);
        Map<EventList, Integer> discountList = discountCalculator.calculateDayOfWeekDiscount(visitDay, pay);
        Discount discount = new Discount(discountList);
        outputView.showResult(pay, discount);
        showDiscountPrice(discount, pay);
    }

    private void showDiscountPrice(Discount discount, Pay pay) {
        Badge badge = discount.findBadge();
        int totalDiscountPayPrice = pay.getPayMoneyAfterDisCount(discount);
        outputView.showAfterDiscountPrice(totalDiscountPayPrice);
        outputView.showEventBadge(badge);
    }
}
