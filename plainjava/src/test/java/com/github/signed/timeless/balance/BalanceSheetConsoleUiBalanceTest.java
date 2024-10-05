package com.github.signed.timeless.balance;

import org.hamcrest.Matchers;
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
        assertThat(positiveBalanceString(), equalTo("+   59"));
        assertThat(negativeBalanceString(), equalTo("-   59"));
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

    private String positiveBalanceString() {
        return callBalanceToString(balance.abs());
    }

    private String negativeBalanceString() {
        return callBalanceToString(balance.abs().negated());
    }

    private String callBalanceToString(final Duration duration) {
        final var result = BalanceSheetConsoleUi.balanceToString(duration);
        assertThat(result, Matchers.hasLength(6));
        return result;
    }

}
