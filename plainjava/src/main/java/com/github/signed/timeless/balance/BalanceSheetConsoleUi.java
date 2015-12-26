package com.github.signed.timeless.balance;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;

import java6.util.function.Predicate;

public class BalanceSheetConsoleUi {

    private Predicate<BalanceRow> printPredicate = new Predicate<BalanceRow>() {
        @Override
        public boolean test(BalanceRow balanceRows) {
            return true;
        }
    };

    public void onlyDisplayWithNegativeBalance() {
        this.printPredicate = new Predicate<BalanceRow>() {
            @Override
            public boolean test(BalanceRow balanceRows) {
                return balanceRows.balance().compareTo(Duration.ZERO) < 0;
            }
        };
    }

    public void print(BalanceSheet balanceSheet) {
        for (BalanceRow balanceRow : balanceSheet) {
            if (DateTimeConstants.MONDAY == balanceRow.day().dayOfWeek().get()) {
                System.out.println("");
            }
            String dayAsString = balanceRow.day().toString("E yyyy.MM.dd");
            if( printPredicate.test(balanceRow)){
                System.out.println(dayAsString + ": " + balanceRow.balance().toPeriod().toString());
            }
        }
    }
}
