package com.github.signed.timeless.workhours;

import com.github.signed.timeless.holidays.HolidayMother;
import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.joda.time.Duration.*;

class WorkHoursPerDayBuilderTest {

    private final WorkHoursPerDayBuilder builder = new WorkHoursPerDayBuilder();

    @Test
    void withNoReductionsWorkHoursAreReturned() {
        builder.hoursToWork(standardHours(2));

        assertThat(durationToWork(), is(standardHours(2)));
    }

    @Test
    void takeReduceHalfADayIntoAccount() {
        builder.hoursToWork(standardHours(8));
        builder.reduceByHalfAWorkDay();

        assertThat(durationToWork(), is(standardHours(4)));
    }

    @Test
    void ifReducedTwiceByHalfAWorkDayMakesAFreeDay() {
        builder.hoursToWork(standardHours(8));
        builder.reduceByHalfAWorkDay();
        builder.reduceByHalfAWorkDay();

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    void takeReduceFullDayIntoAccount() {
        builder.hoursToWork(standardMinutes(30));
        builder.reduceByCompleteWorkDay();

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    void doNotGoBelowZero() {
        builder.hoursToWork(standardHours(1));
        builder.reduceByCompleteWorkDay();
        builder.reduceByHalfAWorkDay();

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    void holidaysAreAlwaysFree() {
        builder.hoursToWork(standardDays(8));
        builder.holiday(HolidayMother.anyHoliday());

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    void operationsCanBePerformedInRandomOrder() {
        builder.reduceByHalfAWorkDay();
        builder.hoursToWork(Duration.standardMinutes(30));

        assertThat(durationToWork(), is(Duration.standardMinutes(15)));
    }

    private Duration durationToWork() {
        return builder.build().duration();
    }
}
