package com.github.signed.timeless.balance;

import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.Duration;
import com.github.signed.timeless.time.Interval;
import java6.util.function.Predicate;
import java8.util.stream.StreamSupport;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.github.signed.timeless.Constants.inputTimeZone;
import static java8.util.stream.Collectors.joining;

public class BalanceSheetConsoleUi {

    public static String balanceToString(Duration balance) {
        final var absBalance = balance.abs();
        if (Duration.ZERO().equals(absBalance)) {
            return "      ";
        }
        return balance.asBalanceString();
    }

    public static String hoursWorkedToString(final Duration timeWorked) {

        if (Duration.ZERO().equals(timeWorked)) {
            return "     ";
        }
        return timeWorked.asHoursWorkedString();
    }


    private final DateTimeZone uiTimeZone = inputTimeZone();

    private Predicate<BalanceRow> printPredicate = balanceRows -> true;

    public void onlyDisplayWithNegativeBalance() {
        this.printPredicate = balanceRows -> balanceRows.balance().compareTo(Duration.ZERO()) < 0;
    }

    public void print(BalanceSheet balanceSheet) {
        printTo(System.out, balanceSheet);
    }

    public void printTo(PrintStream out, BalanceSheet balanceSheet) {
        final var weeks = balanceSheet.weeklyBalance();
        final var weeksToPrint = 3;
        final var toPrint = weeks.subList(Math.max(weeks.size() - weeksToPrint, 0), weeks.size());
        for (WeeklyBalance weeklyBalance : toPrint) {
            var weekRows = new ArrayList<String>();
            for (BalanceRow balanceRow : weeklyBalance) {
                if (printPredicate.test(balanceRow)) {
                    weekRows.add(workdayToString(balanceRow));
                }
            }
            weekRows.forEach(out::println);
            final var weeklyBalanceLine = "weekly balance:         " + balanceToString(weeklyBalance.balance());

            out.println(weeklyBalanceLine);
            out.println();
        }
        out.println(" total balance:         " + balanceToString(balanceSheet.balance()));
    }

    private String workdayToString(BalanceRow balanceRow) {
        String dayAsString = balanceRow.day().asString();

        List<String> workBlocks = new ArrayList<>();
        for (Interval consecutiveTime : balanceRow.dailyWorkLog().intervalsWorked()) {
            DateTimeFormatter workLogFormatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").appendMinuteOfHour(2).toFormatter();
            workBlocks.add(consecutiveTime.getStart().toDateTime(uiTimeZone).asString(workLogFormatter) + "-" + consecutiveTime.getEnd().toDateTime(uiTimeZone).asString(workLogFormatter));
        }
        final var hours = hoursWorkedToString(balanceRow.timeWorked());

        return MessageFormat.format("{0}:  {1}  {2}\t\t{3}", dayAsString, hours, balanceToString(balanceRow.balance()), StreamSupport.stream(workBlocks).collect(joining("  ")));
    }
}
