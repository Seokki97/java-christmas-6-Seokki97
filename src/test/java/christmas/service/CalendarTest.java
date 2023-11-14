package christmas.service;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    private final CalendarService calendarService = new CalendarService();

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

    @DisplayName("방문 날짜가 별표시된 날짜인지 확인한다.")
    @Test
    void isStarDay(){
        int starDay = 10;
        int christmas =25;
        int notStarDay = 26;

        Assertions.assertAll(
                () -> Assertions.assertTrue(calendarService.isStarDay(starDay)),
                () -> Assertions.assertTrue(calendarService.isStarDay(christmas)),
                () -> Assertions.assertFalse(calendarService.isStarDay(notStarDay))
        );
    }

    @DisplayName("방문 날짜의 요일을 반환한다")
    @Test
    void getVisitDayOfWeek(){
        int friday = 6;

        Assertions.assertEquals(friday,calendarService.findVisitDayOfWeek(1));
    }
}
