package com.github.signed.timeless.storage;

import com.github.signed.timeless.contract.Contract;
import com.github.signed.timeless.contract.ContractMother;
import com.github.signed.timeless.contract.ContractsOnRecord;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.signed.timeless.contract.ContractMother.fiveHoursEveryDayNoHolidays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.joda.time.Duration.standardHours;

class WorkYearTest {

    @Test
    void balanceUntilForDateBeforeYearStartIsEmpty() {
        var contracts = new ContractsOnRecord(List.of(Contract.sampleContract()));

        var futureWorkYear = new WorkYear(contracts, 2026);
        var balanceRows = futureWorkYear.balanceUntil(new LocalDate(2025, 1, 1));
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
        assertThat(workYear.balanceUntil(new LocalDate(2026, 1, 1)).balance(), equalTo(Duration.ZERO));;
        assertThat(workYear.balanceUntil(new LocalDate(2026, 1, 2)).balance(), equalTo(standardHours(1)));;
    }

}