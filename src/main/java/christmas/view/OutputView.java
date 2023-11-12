package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.EventList;
import christmas.domain.Order;
import christmas.service.DiscountCalculator;
import christmas.service.Pay;
import java.util.List;
import java.util.Map.Entry;

public class OutputView {

    public void showOrderMenu(List<Order> orderList) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println("");
        System.out.println("<주문 메뉴>");
        for (Order order : orderList) {
            System.out.println(order.toString());
        }
    }

    public void showTotalPrice(Pay totalPriceBeforeDiscount) {
        System.out.println("");
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(totalPriceBeforeDiscount.getTotalPay() + "원");
    }

    public void showGift(Pay pay) {
        System.out.println("");
        System.out.println("<증정 메뉴>");
        System.out.println(EventList.validateCanGetGift(pay));
    }

    public void showBonusList(Discount discount) {
        System.out.println("");
        System.out.println("<혜택 내역>");
        for (Entry<EventList, Integer> entrySet : discount.getDiscountList().entrySet()) {
            System.out.println(entrySet.getKey().getEventName() + "-" + entrySet.getValue() + "원");
        }
    }

    public void showTotalDiscountPrice(Discount discount) {
        System.out.println("");
        System.out.println("<총혜택 금액>");
        System.out.println("-" + discount.calculateTotalDiscountPrice() + "원");
    }

    public void showAfterDiscountPrice(int price) {
        System.out.println("");
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(price + "원");
    }

    public void showEventBadge(Badge badge) {
        System.out.println("");
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getBadgeName());
    }
}
