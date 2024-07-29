package com.github.signed.timeless.balance;

import static com.github.signed.timeless.Constants.frontendTimeZone;
import static java8.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java6.util.function.Predicate;
import java8.util.stream.StreamSupport;

public class BalanceSheetConsoleUi {

    private final DateTimeZone uiTimeZone = frontendTimeZone();

    private Predicate<BalanceRow> printPredicate = new Predicate<>() {
        @Override
        public boolean test(BalanceRow balanceRows) {
            return true;
        }
    };

    public void onlyDisplayWithNegativeBalance() {
        this.printPredicate = new Predicate<>() {
            @Override
            public boolean test(BalanceRow balanceRows) {
                return balanceRows.balance().compareTo(Duration.ZERO) < 0;
            }
        };
    }

    public void print(BalanceSheet balanceSheet) {
        for (WeeklyBalance weeklyBalance : balanceSheet.weeklyBalance()) {
            for (BalanceRow balanceRow : weeklyBalance) {
                if (printPredicate.test(balanceRow)) {
                    printWorkDay(balanceRow);
                }
            }
            System.out.println("weekly balance: " + balanceToString(weeklyBalance.balance()));
            System.out.println("");
        }
    }

    private void printWorkDay(BalanceRow balanceRow) {
        String dayAsString = balanceRow.day().toString("E yyyy.MM.dd");

        List<String> workBlocks = new ArrayList<>();
        for (Interval consecutiveTime : balanceRow.dailyWorkLog().intervalsWorked()) {
            DateTimeFormatter workLogFormatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").appendMinuteOfHour(2).toFormatter();
            workBlocks.add(consecutiveTime.getStart().toDateTime(uiTimeZone).toString(workLogFormatter) + "-" +consecutiveTime.getEnd().toDateTime(uiTimeZone).toString(workLogFormatter));
        }

        System.out.println(dayAsString + ": " + balanceToString(balanceRow.balance()) +"\t\t"+ StreamSupport.stream(workBlocks).collect(joining("  ")));
    }

    private String balanceToString(Duration balance) {
        PeriodFormatter formatter = new PeriodFormatterBuilder().minimumPrintedDigits(2).appendHours().appendSuffix("H").appendSeparatorIfFieldsBefore(" ").appendMinutes().appendSuffix("M").toFormatter();
        return String.format("%10s", balance.toPeriod().toString(formatter));
    }
}
