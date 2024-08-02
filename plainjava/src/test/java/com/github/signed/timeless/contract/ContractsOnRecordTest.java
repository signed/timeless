package com.github.signed.timeless.contract;

import com.github.signed.timeless.Constants;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.signed.timeless.contract.Contract.infinity;
import static com.github.signed.timeless.storage.DateTimeMother.AnyLocalDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ContractsOnRecordTest {

    @Test
    void copeWithNoContracts() {
        final var builder = new WorkHoursPerDayBuilder();
        new ContractsOnRecord(List.of()).adjustHoursToWorkFor(AnyLocalDate(), builder);
        assertThat(builder.build().duration(), equalTo(Duration.ZERO));
    }

    @Test
    void applyHoursToWorkFromContract() {
        final var oneHourPerDayContract = new Contract(infinity(), List.of((date, workHoursPerDayBuilder) -> workHoursPerDayBuilder.hoursToWork(Duration.standardHours(1))));
        final var builder = new WorkHoursPerDayBuilder();
        new ContractsOnRecord(List.of(oneHourPerDayContract)).adjustHoursToWorkFor(AnyLocalDate(), builder);
        assertThat(builder.build().duration(), equalTo(Duration.standardHours(1)));
    }

    @Test
    void onlyApplyTheActiveContract() {
        final var first = new LocalDate(2025, 7, 1);
        final var oneHour = ondDayContract(first, 1);
        final var second = new LocalDate(2025, 7, 2);
        final var twoHoursNextDay = ondDayContract(second, 2);
        final var contracts = new ContractsOnRecord(List.of(oneHour, twoHoursNextDay));

        final var firstDayBuilder = new WorkHoursPerDayBuilder();
        contracts.adjustHoursToWorkFor(first, firstDayBuilder);
        assertThat(firstDayBuilder.build().duration(), equalTo(Duration.standardHours(1)));

        final var secondDayBuilder = new WorkHoursPerDayBuilder();
        contracts.adjustHoursToWorkFor(second, secondDayBuilder);
        assertThat(secondDayBuilder.build().duration(), equalTo(Duration.standardHours(2)));
    }

    private static Contract ondDayContract(LocalDate day, int hours) {
        final var start = day.toDateTimeAtStartOfDay(Constants.frontendTimeZone());
        final var end = day.plusDays(1).toDateTimeAtStartOfDay(Constants.frontendTimeZone());
        final var term = new Interval(start, end);
        return new Contract(term, List.of((date, workHoursPerDayBuilder) -> workHoursPerDayBuilder.hoursToWork(Duration.standardHours(hours))));
    }

}
