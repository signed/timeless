package com.github.signed.timeless.balance;

import static com.github.signed.timeless.workhours.WorkHoursPerDay.unreducedWorkHours;
import static com.github.signed.timeless.storage.DateTimeBuilder.AnyDateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.standardHours;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.storage.DateTimeBuilder;
import com.github.signed.timeless.storage.WorkLogBuilder;

public class BalanceCalculator_SingleDayTest {
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder();
    private final HoursRequired hoursRequired = Mockito.mock(HoursRequired.class);
    private final DateTimeBuilder day = AnyDateTime();

    @Test
    public void canCalculateForThreeWorkPeriodsADay() throws Exception {
        workLogBuilder.on(day).workedFrom("10:00-11:00", "12:00-13:00", "14:00-15:00");
        haveToWorkHours(8);

        assertThat(balanceSheet().requiredToWork, is(standardHours(8)));
        assertThat(balanceSheet().timeWorked, is(standardHours(3)));
        assertThat(balanceSheet().balance, is(standardHours(5).negated()));
    }

    @Test
    public void supportOtherWorkHoursBesides8HoursADay() throws Exception {
        workLogBuilder.on(day).workedFrom("10:00-14:00");
        haveToWorkHours(4);

        assertThat(balanceSheet().balance, is(standardHours(0)));
    }

    private void haveToWorkHours(int hours) {
        when(hoursRequired.hoursToWorkAt(day.buildDay())).thenReturn(unreducedWorkHours(standardHours(hours)));
    }

    private BalanceSheet balanceSheet() {
        return new BalanceCalculator(hoursRequired).balanceFor(workLogBuilder.timeCard());
    }

}