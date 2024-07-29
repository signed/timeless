package com.github.signed.timeless.workhours;

import com.github.signed.timeless.storage.DateTimeMother;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static com.github.signed.timeless.storage.DateTimeMother.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class WorkDays_Test {

    @Test
    void workdayIsOneDay() {
        LocalDate workday = AnyWorkday();

        assertThat(workdaysInRange(workday, workday), is(1));
    }

    @Test
    void saturdayIsNoWorkDay() {
        LocalDate saturday = AnySaturday();

        assertThat(workdaysInRange(saturday, saturday), is(0));
    }

    @Test
    void sundayIsNoWorkDay() {
        LocalDate sunday = AnySunday();

        assertThat(workdaysInRange(sunday, sunday), is(0));
    }

    @Test
    void workweekHasFiveWorkDaysFromSundayToSunday() {
        LocalDate monday = AnyMondayAtTheStartOfAFiveDayWorkWeek();
        LocalDate friday = monday.plusDays(4);

        assertThat(workdaysInRange(monday, friday), is(5));
    }

    @Test
    void fridayToMondayAreTwoDays() {
        LocalDate friday = DateTimeMother.AnyWorkdayFridayWhereNextMondayIsAWorkday();
        LocalDate monday = friday.plusDays(3);
        assertThat(workdaysInRange(friday, monday), is(2));
    }

    private int workdaysInRange(LocalDate start, LocalDate end) {
        return new WorkDays().workdays(start, end);
    }
}
