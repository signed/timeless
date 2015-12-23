package com.github.signed.timeless.contract;

import static com.github.signed.timeless.storage.DateTimeMother.AnySaturday;
import static com.github.signed.timeless.storage.DateTimeMother.AnySunday;
import static com.github.signed.timeless.storage.DateTimeMother.AnyWeekDayBetweenMondayAndFriday;
import static com.github.signed.timeless.storage.DateTimeMother.ChristmasOnAWorkDay;
import static com.github.signed.timeless.storage.DateTimeMother.NewYearsEveOnAWeekend;
import static com.github.signed.timeless.storage.DateTimeMother.NewYearsEveOnAWorkDay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.ZERO;
import static org.joda.time.Duration.standardHours;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.storage.DateTimeMother;

public class WorkHoursTest {

    private final WorkHours workHours = new WorkHours();
    private LocalDate day;

    @Test
    public void saturdayIsWorkFree() throws Exception {
        day = AnySaturday();
        assertThat(hoursToWorkAt(day), is(ZERO));
    }

    @Test
    public void sundayIsWorkFree() throws Exception {
        day = AnySunday();
        assertThat(hoursToWorkAt(day), is(ZERO));
    }

    @Test
    public void anyOtherDayOfTheWeekItIsEightHoursOfWorkTime() throws Exception {
        day = AnyWeekDayBetweenMondayAndFriday();
        assertThat(hoursToWorkAt(day), is(normalWorkDay()));
    }

    @Test
    public void christmasIsOnlyHalfADayOfWork() throws Exception {
        assertThat(hoursToWorkAt(ChristmasOnAWorkDay()), is(halfANormalWorkDay()));
    }

    @Test
    public void christmasOnTheWeekendIsStillFree() throws Exception {
        assertThat(hoursToWorkAt(DateTimeMother.ChristmasOnAWeekend()), is(ZERO));
    }

    @Test
    public void newYearsEveIsOnlyHalfADayOfWork() throws Exception {
        assertThat(hoursToWorkAt(NewYearsEveOnAWorkDay()), is(halfANormalWorkDay()));
    }

    @Test
    public void newYearsEveOnTheWeekEndIsStillFree() throws Exception {
        assertThat(hoursToWorkAt(NewYearsEveOnAWeekend()), is(ZERO));
    }

    private Duration halfANormalWorkDay() {
        return normalWorkDay().dividedBy(2);
    }

    private Duration normalWorkDay() {
        return standardHours(8);
    }

    private Duration hoursToWorkAt(LocalDate day) {
        return workHours.hoursToWorkAt(day).duration();
    }
}