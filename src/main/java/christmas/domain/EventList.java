package christmas.domain;

public enum EventList {

    D_DAY("크리스마스 디데이 할인: "),
    WEEKDAY("평일 할인: "),
    SUNDAY("주말 할인: "),
    SPECIAL("특별 할인: "),
    GIFT("증정 이벤트: "),
    NOTHING("없음"),
    CHAMPAGNE("샴페인 1개");

    private final String eventName;

    EventList(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
