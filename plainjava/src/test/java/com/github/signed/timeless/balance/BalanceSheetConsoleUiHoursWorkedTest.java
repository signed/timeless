package com.github.signed.timeless.balance;

import org.junit.jupiter.api.Test;

import static com.github.signed.timeless.balance.BalanceSheetConsoleUi.hoursWorkedToString;
import static com.github.signed.timeless.time.Duration.ZERO;
import static com.github.signed.timeless.time.Duration.standardHours;
import static com.github.signed.timeless.time.Duration.standardMinutes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class BalanceSheetConsoleUiHoursWorkedTest {

    @Test
    void printPlaceholderMatchingLengthOfHoursWorkedInCaseNotWorkIsLoggedForThatDay() {
        assertThat(hoursWorkedToString(ZERO()), equalTo("     "));
    }

    @Test
    void printHoursAndMinutes() {
        assertThat(hoursWorkedToString(standardMinutes(1)).trim(), equalTo("00:01"));
        assertThat(hoursWorkedToString(standardMinutes(59)).trim(), equalTo("00:59"));
        assertThat(hoursWorkedToString(standardHours(1)).trim(), equalTo("01:00"));
        assertThat(hoursWorkedToString(standardHours(1).plus(standardMinutes(2))).trim(), equalTo("01:02"));
    }
}
