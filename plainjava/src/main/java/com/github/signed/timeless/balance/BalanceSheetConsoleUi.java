package com.github.signed.timeless.balance;

import org.joda.time.DateTimeConstants;

public class BalanceSheetConsoleUi {

    public void print(BalanceSheet balanceSheet) {
        for (BalanceRow balanceRow : balanceSheet) {
            if (DateTimeConstants.MONDAY == balanceRow.day().dayOfWeek().get()) {
                System.out.println("");
            }
            String dayAsString = balanceRow.day().toString("E yyyy.MM.dd");
            System.out.println(dayAsString + ": " + balanceRow.balance().toPeriod().toString());
        }
    }
}
