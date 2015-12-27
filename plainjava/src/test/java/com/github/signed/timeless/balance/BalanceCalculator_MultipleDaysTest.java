package com.github.signed.timeless.balance;

import static com.github.signed.timeless.storage.DateTimeMother.AnyMondayAtTheStartOfAFiveDayWorkWeek;
import static com.github.signed.timeless.workhours.WorkHoursPerDay.unreducedWorkHours;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.joda.time.Duration.standardHours;
import static org.mockito.Mockito.when;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.storage.WorkLogBuilder;

public class BalanceCalculator_MultipleDaysTest {

    private WorkLogBuilder workLogBuilder = new WorkLogBuilder();

    @Test
    public void calculateBalanceFromFirstToLastDayOfATimeSheetAndConsiderDaysWithoutHoursWorked() throws Exception {
        HoursRequired mock = Mockito.mock(HoursRequired.class);
        when(mock.hoursToWorkAt(Mockito.any(LocalDate.class))).thenReturn(unreducedWorkHours(standardHours(1)));

        LocalDate monday = AnyMondayAtTheStartOfAFiveDayWorkWeek();

        workLogBuilder.on(monday).workedFrom("10:00-11:00");
        workLogBuilder.on(monday.plusDays(1)).didNotWork();
        workLogBuilder.on(monday.plusDays(2)).workedFrom("10:00-11:00");

        BalanceSheet balanceSheet = new BalanceCalculator(Duration.ZERO, mock).balanceFor(workLogBuilder.timeCard());

        assertThat(balanceSheet.balance(), is(standardHours(1).negated()));
    }
}