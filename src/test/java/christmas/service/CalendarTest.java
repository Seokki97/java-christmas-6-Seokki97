package christmas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    private CalendarService calendarService = new CalendarService();

    @DisplayName("방문 날짜가 주말인지 확인한다.")
    @Test
    void isWeekend(){
        int sunday = 0;
        int friday = 6;
        int saturday = 7;

        Assertions.assertAll(
                ()-> Assertions.assertTrue(calendarService.isWeekend(friday)),
                () -> Assertions.assertTrue(calendarService.isWeekend(saturday)),
                () -> Assertions.assertFalse(calendarService.isWeekend(sunday))
        );
    }
}
