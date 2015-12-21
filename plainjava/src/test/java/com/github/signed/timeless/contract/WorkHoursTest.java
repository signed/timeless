package com.github.signed.timeless.contract;

import static com.github.signed.timeless.storage.DateTimeMother.AnySaturday;
import static com.github.signed.timeless.storage.DateTimeMother.AnySunday;
import static com.github.signed.timeless.storage.DateTimeMother.AnyWeekDayBetweenMondayAndFriday;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.standardHours;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.Test;

public class WorkHoursTest {

    private final WorkHours workHours = new WorkHours();
    private LocalDate day;

    @Test
    public void saturdayIsWorkFree() throws Exception {
        day = AnySaturday();
        assertThat(hoursToWorkAtGivenDay(day), is(Duration.ZERO));
    }

    @Test
    public void sundayIsWorkFree() throws Exception {
        day = AnySunday();
        assertThat(hoursToWorkAtGivenDay(day), is(Duration.ZERO));
    }

    @Test
    public void anyOtherDayOfTheWeekItIsEightHoursOfWorkTime() throws Exception {
        day = AnyWeekDayBetweenMondayAndFriday();
        assertThat(hoursToWorkAtGivenDay(day), is(standardHours(8)));
    }

    private Duration hoursToWorkAtGivenDay(LocalDate day) {
        return workHours.hoursToWorkAt(day);
    }
}