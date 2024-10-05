package com.github.signed.timeless.balance;

import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class BalanceSheetConsoleUiTest {

    private Duration balance;

    @Test
    void forZeroBalanceOnlyPrintMinutes() {
        balance = Duration.ZERO;
        assertThat(positiveBalanceString(), equalTo("       00M"));
    }

    @Test
    void balanceBelowOneHourOnlyPrintMinutes() {
        balance = Duration.standardMinutes(59);
        assertThat(positiveBalanceString(), equalTo("       59M"));
    }

    @Test
    void balanceAboveOneHourPrintHoursAndMinutes() {
        balance = Duration.standardHours(1).plus(Duration.standardMinutes(1));

        assertThat(positiveBalanceString(), equalTo("   01H 01M"));
        assertThat(negativeBalanceString(), equalTo(" -01H -01M"));
    }

    @Test
    void includeZeroMinutesForExactHourBalance() {
        balance = Duration.standardHours(1);

        assertThat(positiveBalanceString(), equalTo("   01H 00M"));
        assertThat(negativeBalanceString(), equalTo("  -01H 00M"));
    }

    private String negativeBalanceString() {
        return BalanceSheetConsoleUi.balanceToString(balance.negated());
    }

    private String positiveBalanceString() {
        return BalanceSheetConsoleUi.balanceToString(balance);
    }

}
