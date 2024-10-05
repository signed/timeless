package com.github.signed.timeless.balance;

import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import static com.github.signed.timeless.balance.BalanceSheetConsoleUi.hoursWorkedToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class BalanceSheetConsoleUi_HoursWorkedTest {

    @Test
    void printPlaceholderMatchingLengthOfHoursWorkedInCaseNotWorkIsLoggedForThatDay() {
        assertThat(hoursWorkedToString(Duration.ZERO), equalTo("     "));
    }

    @Test
    void printHoursAndMinutes() {
        assertThat(hoursWorkedToString(Duration.standardMinutes(1)).trim(), equalTo("00:01"));
        assertThat(hoursWorkedToString(Duration.standardMinutes(59)).trim(), equalTo("00:59"));
        assertThat(hoursWorkedToString(Duration.standardHours(1)).trim(), equalTo("01:00"));
        assertThat(hoursWorkedToString(Duration.standardHours(1).plus(Duration.standardMinutes(2))).trim(), equalTo("01:02"));
    }
}
