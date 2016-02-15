package com.github.signed.timeless.workhours;

import static com.github.signed.timeless.storage.DateTimeMother.AnyMondayAtTheStartOfAFiveDayWorkWeek;
import static com.github.signed.timeless.storage.DateTimeMother.AnySaturday;
import static com.github.signed.timeless.storage.DateTimeMother.AnySunday;
import static com.github.signed.timeless.storage.DateTimeMother.AnyWorkday;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.storage.DateTimeMother;

public class WorkDays_Test {

    @Test
    public void workdayIsOneDay() throws Exception {
        LocalDate workday = AnyWorkday();

        assertThat(workdaysInRange(workday, workday), is(1));
    }

    @Test
    public void saturdayIsNoWorkDay() throws Exception {
        LocalDate saturday = AnySaturday();

        assertThat(workdaysInRange(saturday, saturday), is(0));
    }

    @Test
    public void sundayIsNoWorkDay() throws Exception {
        LocalDate sunday = AnySunday();

        assertThat(workdaysInRange(sunday, sunday), is(0));
    }

    @Test
    public void workweekHasFiveWorkDaysFromSundayToSunday() throws Exception {
        LocalDate monday = AnyMondayAtTheStartOfAFiveDayWorkWeek();
        LocalDate friday = monday.plusDays(4);

        assertThat(workdaysInRange(monday, friday), is(5));
    }

    @Test
    public void fridayToMondayAreTwoDays() throws Exception {
        LocalDate friday = DateTimeMother.AnyWorkdayFridayWhereNextMondayIsAWorkday();
        LocalDate monday = friday.plusDays(3);
        assertThat(workdaysInRange(friday, monday), is(2));
    }

    private int workdaysInRange(LocalDate start, LocalDate end) {
        return new WorkDays().workdays(start, end);
    }
}