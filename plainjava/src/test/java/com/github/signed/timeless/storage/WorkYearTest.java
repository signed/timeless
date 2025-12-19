package com.github.signed.timeless.storage;

import com.github.signed.timeless.contract.Contract;
import com.github.signed.timeless.contract.ContractsOnRecord;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class WorkYearTest {

    @Test
    void balanceUntilForDateBeforeYearStartIsEmpty() {
        var contracts = new ContractsOnRecord(List.of(Contract.sampleContract()));

        var futureWorkYear = new WorkYear(contracts, 2026);
        var balanceRows = futureWorkYear.balanceUntil(new LocalDate(2025, 1, 1));
        assertThat(balanceRows, notNullValue());
    }
}