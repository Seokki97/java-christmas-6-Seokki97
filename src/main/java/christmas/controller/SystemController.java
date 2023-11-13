package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.EventList;
import christmas.domain.Order;
import christmas.service.DiscountCalculator;
import christmas.service.OrderService;
import christmas.service.Pay;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class SystemController {

    private final OutputView outputView;
    private final OrderController orderController;
    private final OrderService orderService;
    private final DiscountCalculator discountCalculator;

    public SystemController(final OutputView outputView) {
        this.outputView = outputView;
        this.orderService = new OrderService();
        this.orderController = new OrderController(new InputView(), outputView);
        this.discountCalculator = new DiscountCalculator();
    }

    public void run() {
        int visitDay = orderController.readVisitDay();
        List<Order> orders = orderController.readOrderMenu();
        int totalPrice = orderService.calculateTotalPrice(orders);
        Pay pay = new Pay(totalPrice);
        Map<EventList, Integer> discountList = discountCalculator.calculateDayOfWeekDiscount(visitDay, orders, pay);
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
