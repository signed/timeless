package com.github.signed.timeless.storage;

import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.balance.BalanceSheetConsoleUi;

class MyHoursWorked_Test {
    private final BalanceSheetConsoleUi consoleUi = new BalanceSheetConsoleUi();


    @Test
    void sampleUsage() {
        WorkYear workYear = new WorkYear(2017) {

            @Override
            protected void january(DateTimeBuilder january) {
                on(january.the(1)).workedFrom("23:59-00:01");
                dayOfUsingOvertime(january.the(2));
            }
        };

        printBalance(workYear);
    }

    private void printBalance(WorkYear workYear) {
        BalanceSheet balanceSheet = workYear.balanceUpUntilToday();
        consoleUi.print(balanceSheet);

        System.out.println();
        System.out.println("required to work: " + asString(balanceSheet.requiredToWork()));
        System.out.println("time worked     : " + asString(balanceSheet.timeWorked()));
    }

    private String asString(Duration duration) {
        return duration.toPeriod().toString();
    }

}