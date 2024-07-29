package com.github.signed.timeless;

import com.github.signed.timeless.balance.BalanceCalculator;
import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.contract.EmployerCourtesy;
import com.github.signed.timeless.contract.WorkHours;
import com.github.signed.timeless.holidays.Holidays;
import com.github.signed.timeless.storage.DateTimeBuilder;
import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.storage.WorkLogBuilder;
import com.github.signed.timeless.workhours.DaysOffAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayCompendium;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.Duration.standardHours;

class Integration_Test {
    private final WorkHours workHours = new WorkHours();
    private final DaysOffAdjuster personalTimeOff = new DaysOffAdjuster();
    private final Holidays holidays = new Holidays();
    private final DaysOffAdjuster conferenceDays = new DaysOffAdjuster();
    private final DaysOffAdjuster sickLeave = new DaysOffAdjuster();
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder().inLocalTime();

    @Test
    void smoke_test_after_wiring_everything() {
        LocalDate workday = DateTimeMother.AnyWorkday();
        DateTimeBuilder day = DateTimeBuilder.At(workday);

        WorkLogBuilder workLogBuilder = this.workLogBuilder.on(day);
        workLogBuilder.workedFrom("10:00-15:00");
        personalTimeOff.halfADayOffAt(workday);

        assertThat(balance(workLogBuilder), is(standardHours(1)));
    }

    @Test
    void compendium_works_properly_on_days_that_have_not_the_normal_amount_of_required_hours_to_work() {
        LocalDate day = DateTimeMother.AnyChristmasEveOnAWorkDay();
        personalTimeOff.halfADayOffAt(day);

        assertThat(completeCompendium().hoursToWorkAt(day).duration(), is(Duration.standardHours(0)));
    }

    private Duration balance(WorkLogBuilder workLogBuilder) {
        HoursRequired compendium = completeCompendium();
        BalanceCalculator balanceCalculator = new BalanceCalculator(compendium, Constants.frontendTimeZone());
        BalanceSheet balanceSheet = balanceCalculator.balanceFor(workLogBuilder.timeCard());

        return balanceSheet.balance();
    }

    private HoursRequired completeCompendium() {
        HashSet<WorkHoursPerDayAdjuster> adjusters = new HashSet<>();
        adjusters.add(workHours);
        adjusters.add(personalTimeOff);
        adjusters.add(holidays);
        adjusters.add(conferenceDays);
        adjusters.add(sickLeave);
        adjusters.add(new EmployerCourtesy());
        return new WorkHoursPerDayCompendium(adjusters);
    }
}
