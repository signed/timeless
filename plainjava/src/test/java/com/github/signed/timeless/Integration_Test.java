package com.github.signed.timeless;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.standardHours;

import java.util.HashSet;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.balance.BalanceCalculator;
import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.contract.WorkHours;
import com.github.signed.timeless.storage.DateTimeBuilder;
import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.storage.WorkLogBuilder;
import com.github.signed.timeless.workhours.ConferenceDays;
import com.github.signed.timeless.workhours.Holidays;
import com.github.signed.timeless.workhours.PersonalTimeOff;
import com.github.signed.timeless.workhours.SickLeave;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayCompendium;

public class Integration_Test {
    private final WorkHours workHours = new WorkHours();
    private final PersonalTimeOff personalTimeOff = new PersonalTimeOff();
    private final Holidays holidays = new Holidays();
    private final ConferenceDays conferenceDays = new ConferenceDays();
    private final SickLeave sickLeave = new SickLeave();
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder().inLocalTime();

    @Test
    public void smoke_test_after_wiring_everything() throws Exception {
        LocalDate workday = DateTimeMother.AnyWorkday();
        DateTimeBuilder day = DateTimeBuilder.At(workday);

        WorkLogBuilder workLogBuilder = this.workLogBuilder.on(day);
        workLogBuilder.workedFrom("10:00-15:00");
        personalTimeOff.halfADayOffAt(workday);

        assertThat(balance(workLogBuilder), is(standardHours(1)));
    }

    private Duration balance(WorkLogBuilder workLogBuilder) {
        HoursRequired compendium = completeCompendium();
        BalanceCalculator balanceCalculator = new BalanceCalculator(compendium);
        BalanceSheet balanceSheet = balanceCalculator.balanceFor(workLogBuilder.timeCard());

        return balanceSheet.balance();
    }

    private HoursRequired completeCompendium() {
        HashSet<WorkHoursPerDayAdjuster> adjusters = new HashSet<WorkHoursPerDayAdjuster>();
        adjusters.add(workHours);
        adjusters.add(personalTimeOff);
        adjusters.add(holidays);
        adjusters.add(conferenceDays);
        adjusters.add(sickLeave);

        return new WorkHoursPerDayCompendium(adjusters);
    }
}
