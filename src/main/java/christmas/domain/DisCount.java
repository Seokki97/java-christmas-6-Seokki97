package christmas.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public record DisCount(Map<String, Integer> disCount) {

    public Map<String, Integer> calculateDiscount(List<Order> orderList, int day, Pay pay) {
        if (!EventList.isEventOnCondition(pay)) {
            disCount.put(EventList.NOTHING.getEventName(), 0);
            return disCount;
        }
        for (EventList eventList : EventList.values()) {
            findEventListByOrder(eventList, orderList, day);
        }
        if (pay.isTotalPayOverGiftEventPrice()) {
            putDiscountElement(EventList.GIFT.getEventName(), 25000);
        }
        return disCount;
    }

    public void findEventListByOrder(EventList eventList, List<Order> orderList, int day) {
        if (eventList.equals(EventList.SUNDAY)) {
            increaseDiscountElement(orderList, eventList, "메인");
        }
        if (eventList.equals(EventList.WEEKDAY)) {
            increaseDiscountElement(orderList, eventList, "디저트");
        }
        if (eventList.equals(EventList.SPECIAL)) {
            putDiscountElement(EventList.SPECIAL.getEventName(), 1000);
        }
        if (eventList.equals(EventList.D_DAY)) {
            int totalCount = (day - 1) * 100;
            putDiscountElement(EventList.D_DAY.getEventName(), 1000 - totalCount);
        }
    }

    public void putDiscountElement(String eventName, int discountMoney) {
        disCount.put(eventName,
                disCount.getOrDefault(eventName, 0) - discountMoney);
    }

    public void increaseDiscountElement(List<Order> orderList, EventList eventList, String type) {
        for (Order order : orderList) {
            if (order.orderItem().isSameType(type)) {
                putDiscountElement(eventList.getEventName(), order.orderCount() * -2023);
            }
        }
    }

    public int getTotalDiscountPrice() {
        int sum = 0;
        for (Entry<String, Integer> entryMap : disCount.entrySet()) {
            sum += entryMap.getValue();
        }
        return sum;
    }

    public boolean hasGiftEvent() {
        return disCount.containsKey(EventList.GIFT.getEventName());
    }
}
