package com.github.signed.timeless;

import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.balance.BalanceSheetConsoleUi;
import com.github.signed.timeless.storage.DateTimeBuilder;
import com.github.signed.timeless.storage.WorkYear;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.io.PrintStream;

class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    private final BalanceSheetConsoleUi consoleUi = new BalanceSheetConsoleUi();

    private void run() {
        WorkYear workYear = new WorkYear(2024) {
            @Override
            public void july(DateTimeBuilder july) {
                daysOffStarting(july.the(1).copy(), july.the(10).copy());
                on(july.the(11)).workedFrom("17:30-22:16");
                on(july.the(14)).workedFrom("11:11-12:10", "16:43-19:55");
                on(july.the(15)).workedFrom("13:39-22:54");
                on(july.the(16)).workedFrom("12:00-13:40");
            }
        };

        printBalance(workYear.balanceFor(new LocalDate(2024, 7, 1), new LocalDate()));
    }


    private void printBalance(BalanceSheet balanceSheet) {
        consoleUi.print(balanceSheet);

        PrintStream out = System.out;
        out.println();
        out.println("required to work: " + asString(balanceSheet.requiredToWork()));
        out.println("time worked     : " + asString(balanceSheet.timeWorked()));
        out.println("balance         : " + asString(balanceSheet.balance()));
    }

    private String asString(Duration duration) {
        return duration.toPeriod().toString();
    }
}
