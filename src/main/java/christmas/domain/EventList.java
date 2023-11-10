package christmas.domain;

public enum EventList {
    WEEKDAY("평일 할인: ", -2023),
    SUNDAY("주말 할인: ", -2023),
    SPECIAL("특별 할인: ", -1000),
    GIFT("증정 이벤트: ", 0),
    NOTHING("해당 없음", 0);
    private final String eventName;

    private final int discountMoney;

    EventList(String eventName, int discountMoney) {
        this.eventName = eventName;
        this.discountMoney = discountMoney;
    }

    public static EventList findDayEvent(int dayOfWeek) {
        if (dayOfWeek >= 5) {
            return EventList.SUNDAY;
        }
        return EventList.WEEKDAY;
    }

    public static EventList isStarEvent(int day) {
        if (day % 7 == 3) {
            return EventList.SPECIAL;
        }
        if (day == 25) {
            return EventList.SPECIAL;
        }
        return EventList.NOTHING;
    }

    public static EventList isGiftEvent(Pay pay) {
        if (pay.isTotalPayOverGiftEventPrice()) {
            return EventList.GIFT;
        }
        return EventList.NOTHING;
    }

    public int getDiscountMoney() {
        return discountMoney;
    }

    public String getEventName(){
     return eventName;
    }
}
