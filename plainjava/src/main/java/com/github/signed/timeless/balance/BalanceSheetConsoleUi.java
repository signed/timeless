package com.github.signed.timeless.balance;

import java6.util.function.Predicate;
import java8.util.stream.StreamSupport;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.signed.timeless.Constants.frontendTimeZone;
import static java8.util.stream.Collectors.joining;

public class BalanceSheetConsoleUi {

    private static final PeriodFormatter HourFormatter = new PeriodFormatterBuilder()
            .minimumPrintedDigits(2).printZeroNever().appendHours()
            .toFormatter();
    private static final PeriodFormatter MinuteFormatter = new PeriodFormatterBuilder()
            .minimumPrintedDigits(2)
            .printZeroIfSupported().appendMinutes()
            .toFormatter();

    public static String balanceToString(Duration balance) {
        boolean isNegative = balance.isShorterThan(Duration.ZERO);
        final var absBalance = balance.abs();
        if (Duration.ZERO.equals(absBalance)) {
            return "      ";
        }

        final var period = absBalance.toPeriod();
        final var hour = period.toString(HourFormatter);
        final var minute = period.toString(MinuteFormatter);
        return Stream.of(hour, minute)
                .skip(hoursFor(absBalance))
                .collect(Collectors.joining(":", isNegative ? "-" : "+", ""));
    }

    private static int hoursFor(Duration absBalance) {
        final var atLeastAnHour = !Duration.standardHours(1).isLongerThan(absBalance);
        return atLeastAnHour ? 0 : 1;
    }

    private static final PeriodFormatter hoursWorkedFormatter = new PeriodFormatterBuilder().minimumPrintedDigits(2).printZeroIfSupported().appendHours().appendLiteral(":").appendMinutes().toFormatter();

    public static String hoursWorkedToString(final Duration timeWorked) {

        if (Duration.ZERO.equals(timeWorked)) {
            return "     ";
        }
        final var period = timeWorked.toPeriod();
        return period.toString(hoursWorkedFormatter);
    }

    private static String fourCharacters(String input) {
        return String.format("%4s", input);
    }

    private final DateTimeZone uiTimeZone = frontendTimeZone();

    private Predicate<BalanceRow> printPredicate = balanceRows -> true;

    public void onlyDisplayWithNegativeBalance() {
        this.printPredicate = balanceRows -> balanceRows.balance().compareTo(Duration.ZERO) < 0;
    }

    public void print(BalanceSheet balanceSheet) {
        for (WeeklyBalance weeklyBalance : balanceSheet.weeklyBalance()) {
            for (BalanceRow balanceRow : weeklyBalance) {
                if (printPredicate.test(balanceRow)) {
                    printWorkDay(balanceRow);
                }
            }
            final var weeklyBalanceLine = "weekly balance:         " + balanceToString(weeklyBalance.balance());
            System.out.println(weeklyBalanceLine);
            System.out.println();
        }
    }

    private void printWorkDay(BalanceRow balanceRow) {
        String dayAsString = balanceRow.day().toString("E yyyy.MM.dd");

        List<String> workBlocks = new ArrayList<>();
        for (Interval consecutiveTime : balanceRow.dailyWorkLog().intervalsWorked()) {
            DateTimeFormatter workLogFormatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").appendMinuteOfHour(2).toFormatter();
            workBlocks.add(consecutiveTime.getStart().toDateTime(uiTimeZone).toString(workLogFormatter) + "-" + consecutiveTime.getEnd().toDateTime(uiTimeZone).toString(workLogFormatter));
        }
        final var hours = hoursWorkedToString(balanceRow.timeWorked());

        final var line = MessageFormat.format("{0}:  {1}  {2}\t\t{3}", dayAsString, hours, balanceToString(balanceRow.balance()), StreamSupport.stream(workBlocks).collect(joining("  ")));
        System.out.println(line);
    }
}
