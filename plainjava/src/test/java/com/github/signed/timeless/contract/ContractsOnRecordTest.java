package com.github.signed.timeless.contract;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.hamcrest.CoreMatchers;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        final var oneHourPerDayContract = new Contract(List.of(new WorkHoursPerDayAdjuster() {
            @Override
            public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.hoursToWork(Duration.standardHours(1));
            }
        }));
        final var builder = new WorkHoursPerDayBuilder();
        new ContractsOnRecord(List.of(oneHourPerDayContract)).adjustHoursToWorkFor(AnyLocalDate(), builder);
        assertThat(builder.build().duration(), equalTo(Duration.standardHours(1)));
    }
}
