package com.github.signed.timeless.contract;

import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static com.github.signed.timeless.storage.DateTimeMother.AnySaturday;
import static com.github.signed.timeless.storage.DateTimeMother.AnySunday;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.*;
import static org.mockito.Mockito.mock;

class WeeklyWorkHoursTest {
    private LocalDate day;
    private int hoursPerWeek = 40;

    @Test
    void saturdayIsWorkFree() {
        day = AnySaturday();
        assertThat(hoursToWorkAt(day), is(ZERO));
    }

    @Test
    void sundayIsWorkFree() {
        day = AnySunday();
        assertThat(hoursToWorkAt(day), is(ZERO));
    }

    @Test
    void anyOtherDayOfTheWeekOnFifthOfTheWeeklyWorkHours() {
        day = DateTimeMother.AnyWorkday();
        hoursPerWeek = 40;
        assertThat(hoursToWorkAt(day), is(standardHours(8)));

        hoursPerWeek = 39;
        assertThat(hoursToWorkAt(day), is(standardHours(7).plus(standardMinutes(48))));
    }

    private Duration hoursToWorkAt(LocalDate day) {
        WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);
        ArgumentCaptor<Duration> captor = ArgumentCaptor.forClass(Duration.class);
        final WeeklyWorkHours weeklyWorkHours = WeeklyWorkHours.fiveDayWorkWeekOf(hoursPerWeek);
        weeklyWorkHours.adjustHoursToWorkFor(day, builder);
        Mockito.verify(builder).hoursToWork(captor.capture());
        return captor.getValue();
    }
}
