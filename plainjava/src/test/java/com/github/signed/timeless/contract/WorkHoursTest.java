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
import static org.joda.time.Duration.ZERO;
import static org.joda.time.Duration.standardHours;
import static org.mockito.Mockito.mock;

class WorkHoursTest {

    private final WorkHours workHours = new WorkHours();
    private LocalDate day;

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
    void anyOtherDayOfTheWeekItIsEightHoursOfWorkTime() {
        day = DateTimeMother.AnyWorkday();
        assertThat(hoursToWorkAt(day), is(normalWorkDay()));
    }

    private Duration normalWorkDay() {
        return standardHours(8);
    }

    private Duration hoursToWorkAt(LocalDate day) {
        WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);
        ArgumentCaptor<Duration> captor = ArgumentCaptor.forClass(Duration.class);
        workHours.adjustHoursToWorkFor(day, builder);
        Mockito.verify(builder).hoursToWork(captor.capture());
        return captor.getValue();
    }
}
