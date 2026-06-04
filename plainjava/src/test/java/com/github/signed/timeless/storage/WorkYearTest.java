package com.github.signed.timeless.storage;

import com.github.signed.timeless.contract.Contract;
import com.github.signed.timeless.contract.ContractsOnRecord;
import com.github.signed.timeless.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.signed.timeless.contract.ContractMother.fiveHoursEveryDayNoHolidays;
import static com.github.signed.timeless.time.Duration.ZERO;
import static com.github.signed.timeless.time.Duration.standardHours;
import static com.github.signed.timeless.time.Month.January;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class WorkYearTest {

    @Test
    void balanceUntilForDateBeforeYearStartIsEmpty() {
        var contracts = new ContractsOnRecord(List.of(Contract.sampleContract()));

        var futureWorkYear = new WorkYear(contracts, 2026);
        var balanceRows = futureWorkYear.balanceUntil(LocalDate.of(2025, January, 1));
        assertThat(balanceRows, notNullValue());
    }

    @Test
    void calculateBalancesForDifferentIntervals() {

        var contracts = new ContractsOnRecord(List.of(fiveHoursEveryDayNoHolidays()));

        var workYear = new WorkYear(contracts, 2026){
            @Override
            public void january(DateTimeBuilder january) {
                on(january.the(1)).workedFrom("10:00-15:00");
                on(january.the(2)).workedFrom("10:00-16:00");
            }
        };
        assertThat(workYear.balanceUntil(LocalDate.of(2026, January, 1)).balance(), equalTo(ZERO()));;
        assertThat(workYear.balanceUntil(LocalDate.of(2026, January, 2)).balance(), equalTo(standardHours(1)));;
    }

}