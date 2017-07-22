package com.github.signed.timeless.storage;

import org.joda.time.Duration;
import org.junit.Ignore;
import org.junit.Test;

import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.balance.BalanceSheetConsoleUi;

public class MyHoursWorked_Test {
    private final BalanceSheetConsoleUi consoleUi = new BalanceSheetConsoleUi();


    @Test
    @Ignore
    public void sampleUsage() throws Exception {
        WorkYear workYear = new WorkYear(2017) {

            @Override
            protected void january(DateTimeBuilder january) {
                on(january.the(1)).workedFrom("23:59-0:01");
            }
        };

        printBalance(workYear);
    }

    private void printBalance(WorkYear workYear) {
        BalanceSheet balanceSheet = workYear.balanceUpUntilToday();
        consoleUi.print(balanceSheet);

        System.out.println("");
        System.out.println("required to work: " + asString(balanceSheet.requiredToWork()));
        System.out.println("time worked     : " + asString(balanceSheet.timeWorked()));
    }

    private String asString(Duration duration) {
        return duration.toPeriod().toString();
    }

}