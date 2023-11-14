package christmas.view;

import static christmas.service.NumberFormatter.convertToOutputFormat;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.EventList;
import christmas.domain.Order;
import christmas.domain.OrderList;
import christmas.domain.Pay;
import java.util.Map.Entry;

public class OutputView {

    public void showOrderMenu(OrderList orderList) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println("");
        System.out.println("<주문 메뉴>");
        for (Order order : orderList.orderList()) {
            System.out.println(order.toString());
        }
    }

    public void showResult(Pay pay, Discount discount) {
        showTotalPrice(pay);
        showGift(pay);
        showBonusList(discount);
        showTotalDiscountPrice(discount.getTotalBenefitMoney());
    }

    private void showTotalPrice(Pay totalPriceBeforeDiscount) {
        System.out.println("");
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(convertToOutputFormat(totalPriceBeforeDiscount.getTotalPay()) + "원");
    }

    private void showGift(Pay pay) {
        System.out.println("");
        System.out.println("<증정 메뉴>");
        System.out.println(pay.checkCanGetGift());
    }

    private void showBonusList(Discount discount) {
        System.out.println("");
        System.out.println("<혜택 내역>");
        for (Entry<EventList, Integer> entrySet : discount.discountList().entrySet()) {
            decideBonusListMessage(entrySet);
        }
    }

    public void decideBonusListMessage(Entry<EventList, Integer> entrySet) {
        if (entrySet.getKey().getEventName().equals("없음")) {
            System.out.println(entrySet.getKey().getEventName());
            return;
        }
        System.out.println(
                entrySet.getKey().getEventName() + "-" + convertToOutputFormat(entrySet.getValue())
                        + "원");
    }

    private void showTotalDiscountPrice(int totalDiscountMoney) {
        System.out.println("");
        System.out.println("<총혜택 금액>");
        decideTotalDiscountPriceMessage(convertToOutputFormat(totalDiscountMoney));
    }

    private void decideTotalDiscountPriceMessage(String money) {
        if (money.equals("0")) {
            System.out.println(money + "원");
            return;
        }
        System.out.println("-" + money + "원");
    }

    public void showAfterDiscountPrice(int price) {
        System.out.println("");
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(convertToOutputFormat(price) + "원");
    }

    public void showEventBadge(Badge badge) {
        System.out.println("");
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getBadgeName());
    }
}
