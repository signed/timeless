package com.github.signed.timeless.balance;

import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class BalanceSheetConsoleUiBalanceTest {

    private Duration balance;

    @Test
    void forZeroBalanceOnlyPrintMinutes() {
        balance = Duration.ZERO;
        assertThat(positiveBalanceString(), equalTo("      "));
    }

    @Test
    void balanceBelowOneHourOnlyPrintMinutes() {
        balance = Duration.standardMinutes(59);
        assertThat(positiveBalanceString(), equalTo("+59"));
    }

    @Test
    void balanceAboveOneHourPrintHoursAndMinutes() {
        balance = Duration.standardHours(1).plus(Duration.standardMinutes(1));

        assertThat(positiveBalanceString(), equalTo("+01:01"));
        assertThat(negativeBalanceString(), equalTo("-01:01"));
    }

    @Test
    void includeZeroMinutesForExactHourBalance() {
        balance = Duration.standardHours(1);

        assertThat(positiveBalanceString(), equalTo("+01:00"));
        assertThat(negativeBalanceString(), equalTo("-01:00"));
    }

    private String negativeBalanceString() {
        return BalanceSheetConsoleUi.balanceToString(balance.negated());
    }

    private String positiveBalanceString() {
        return BalanceSheetConsoleUi.balanceToString(balance);
    }

}
