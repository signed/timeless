package com.github.signed.timeless.balance;

import com.github.signed.timeless.Constants;
import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.storage.DateTimeBuilder;
import com.github.signed.timeless.storage.WorkLogBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.github.signed.timeless.storage.DateTimeBuilder.AnyDateTime;
import static com.github.signed.timeless.workhours.WorkHoursPerDay.unreducedWorkHours;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.standardHours;
import static org.mockito.Mockito.when;

class BalanceCalculator_SingleDayTest {
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder();
    private final HoursRequired hoursRequired = Mockito.mock(HoursRequired.class);
    private final DateTimeBuilder day = AnyDateTime();

    @Test
    void canCalculateForThreeWorkPeriodsADay() {
        workLogBuilder.on(day).workedFrom("10:00-11:00", "12:00-13:00", "14:00-15:00");
        haveToWorkHours(8);
        assertThat(balanceSheet().balance(), is(standardHours(5).negated()));
    }

    @Test
    void supportOtherWorkHoursBesides8HoursADay() {
        workLogBuilder.on(day).workedFrom("10:00-14:00");
        haveToWorkHours(4);

        assertThat(balanceSheet().balance(), is(standardHours(0)));
    }

    private void haveToWorkHours(int hours) {
        when(hoursRequired.hoursToWorkAt(day.buildDay())).thenReturn(unreducedWorkHours(standardHours(hours)));
    }

    private BalanceSheet balanceSheet() {
        return new BalanceCalculator(hoursRequired, Constants.frontendTimeZone()).balanceFor(workLogBuilder.timeCard());
    }

}
