package com.github.signed.timeless.workhours;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.joda.time.Duration.ZERO;
import static org.joda.time.Duration.standardDays;
import static org.joda.time.Duration.standardHours;
import static org.joda.time.Duration.standardMinutes;

import org.joda.time.Duration;
import org.junit.Test;

import com.github.signed.timeless.holidays.HolidayMother;

public class WorkHoursPerDayBuilderTest {

    private final WorkHoursPerDayBuilder builder = new WorkHoursPerDayBuilder();

    @Test
    public void withNoReductionsWorkHoursAreReturned() throws Exception {
        builder.hoursToWork(standardHours(2));

        assertThat(durationToWork(), is(standardHours(2)));
    }

    @Test
    public void takeReduceHalfADayIntoAccount() throws Exception {
        builder.hoursToWork(standardHours(8));
        builder.reduceByHalfAWorkDay();

        assertThat(durationToWork(), is(standardHours(4)));
    }

    @Test
    public void ifReducedTwiceByHalfAWorkDayMakesAFreeDay() throws Exception {
        builder.hoursToWork(standardHours(8));
        builder.reduceByHalfAWorkDay();
        builder.reduceByHalfAWorkDay();

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    public void takeReduceFullDayIntoAccount() throws Exception {
        builder.hoursToWork(standardMinutes(30));
        builder.reduceByCompleteWorkDay();

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    public void doNotGoBelowZero() throws Exception {
        builder.hoursToWork(standardHours(1));
        builder.reduceByCompleteWorkDay();
        builder.reduceByHalfAWorkDay();

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    public void holidaysAreAlwaysFree() throws Exception {
        builder.hoursToWork(standardDays(8));
        builder.holiday(HolidayMother.anyHoliday());

        assertThat(durationToWork(), is(ZERO));
    }

    @Test
    public void operationsCanBePerformedInRandomOrder() throws Exception {
        builder.reduceByHalfAWorkDay();
        builder.hoursToWork(Duration.standardMinutes(30));

        assertThat(durationToWork(), is(Duration.standardMinutes(15)));
    }

    private Duration durationToWork() {
        return builder.build().duration();
    }
}