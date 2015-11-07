package com.github.signed.timeless.balance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.joda.time.Duration;
import org.junit.Test;

import com.github.signed.timeless.TimeCard;
import com.github.signed.timeless.storage.DateTimeBuilder;
import com.github.signed.timeless.storage.WorkLogBuilder;

public class BalanceCalculator_Test {

    @Test
    public void canCalculateForThreeWorkPeriodsADay() throws Exception {
        WorkLogBuilder workLogBuilder = new WorkLogBuilder();
        TimeCard timeCard = new TimeCard(workLogBuilder.on(DateTimeBuilder.AnyDateTime()).workedFrom("10:00-11:00", "12:00-13:00", "14:00-15:00").punches());
        BalanceSheet balanceSheet = new BalanceCalculator().balanceFor(timeCard);
        assertThat(balanceSheet.requiredToWork, is(Duration.standardHours(8)));
        assertThat(balanceSheet.timeWorked, is(Duration.standardHours(3)));
        assertThat(balanceSheet.balance, is(Duration.standardHours(5).negated()));
    }
}