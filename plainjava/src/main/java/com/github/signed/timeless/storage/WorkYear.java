package com.github.signed.timeless.storage;

import com.github.signed.timeless.Constants;
import com.github.signed.timeless.balance.BalanceCalculator;
import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.contract.ContractsOnRecord;
import com.github.signed.timeless.workhours.DaysOffAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayCompendium;
import java6.util.function.Supplier;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkYear {

    private final DaysOffAdjuster personalTimeOff = new DaysOffAdjuster();
    private final DaysOffAdjuster sickLeave = new DaysOffAdjuster();
    private final DaysOffAdjuster conferenceDays = new DaysOffAdjuster();
    private final BalanceCalculator balanceCalculator;
    private final DateTimeZone inputTimeZone = DateTimeZone.getDefault();
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder().inLocalTime(inputTimeZone);
    private final int year;

    public WorkYear(final ContractsOnRecord contracts, int year) {
        WorkHoursPerDayCompendium compendium = new WorkHoursPerDayCompendium(adjusters(contracts, personalTimeOff, sickLeave, conferenceDays));
        balanceCalculator = new BalanceCalculator(compendium, Constants.frontendTimeZone());
        this.year = year;
    }

    private static Set<WorkHoursPerDayAdjuster> adjusters(WorkHoursPerDayAdjuster... adjusters) {
        return new HashSet<>(Arrays.asList(adjusters));
    }

    public BalanceSheet balanceAtEndOfYear() {
        return balanceAtEndOfYearStarting(startThisYear());
    }

    public BalanceSheet balanceUntil(LocalDate until) {
        LocalDate startOfYear = startOfYear();
        return balance(startOfYear, until);
    }

    public BalanceSheet balanceAtEndOfYearStarting(LocalDate start) {
        LocalDate lastDayInYear = startOfNexYear().minusDays(1);
        return balance(start, lastDayInYear);
    }

    public BalanceSheet balanceUpUntilToday() {
        LocalDate today = new LocalDate();
        LocalDate startOfYear = startOfYear();
        LocalDate until = today;
        if (today.isBefore(startOfYear)) {
            until = startOfYear;
        }
        return balance(startOfYear, until);
    }

    public BalanceSheet balanceFor(LocalDate start, LocalDate end) {
        if (start.isBefore(startOfYear())) {
            start = startOfYear();
        }
        if (!end.isBefore(startOfNexYear())) {
            end = startOfNexYear();
        }
        return balance(start, end);
    }

    public void january(DateTimeBuilder january, DateTimeBuilder february) {
        january(january);
    }


    public void january(DateTimeBuilder january) {
        //did not work
    }

    public void february(DateTimeBuilder february, DateTimeBuilder march) {
        february(february);
    }

    public void february(DateTimeBuilder february) {
        //did not work
    }

    public void march(DateTimeBuilder march, DateTimeBuilder april) {
        march(march);
    }

    public void march(DateTimeBuilder march) {
        //did not work
    }

    public void april(DateTimeBuilder april, DateTimeBuilder may) {
        april(april);
    }

    public void april(DateTimeBuilder april) {
        //did not work
    }

    public void may(DateTimeBuilder may, DateTimeBuilder june) {
        may(may);
    }

    public void may(DateTimeBuilder may) {
        //did not work
    }

    public void june(DateTimeBuilder june, DateTimeBuilder july) {
        june(june);
    }

    public void june(DateTimeBuilder june) {
        //did not work
    }

    public void july(DateTimeBuilder july, DateTimeBuilder august) {
        july(july);
    }

    public void july(DateTimeBuilder july) {
        //did not work
    }

    public void august(DateTimeBuilder august, DateTimeBuilder september) {
        august(august);
    }

    public void august(DateTimeBuilder august) {
        //did not work
    }

    public void september(DateTimeBuilder september, DateTimeBuilder october) {
        september(september);
    }

    public void september(DateTimeBuilder september) {
        //did not work
    }

    public void october(DateTimeBuilder october, DateTimeBuilder november) {
        october(october);
    }

    public void october(DateTimeBuilder october) {
        //did not work
    }

    public void november(DateTimeBuilder november, DateTimeBuilder december) {
        november(november);
    }

    public void november(DateTimeBuilder november) {
        //did not work
    }

    public void december(DateTimeBuilder december) {
        //did not work
    }

    protected WorkLogBuilder on(DateTimeBuilder day) {
        return workLogBuilder.on(day);
    }

    protected void halfADayOffOn(DateTimeBuilder day) {
        personalTimeOff.halfADayOffAt(day.buildDay());
    }

    protected void dayOffOn(DateTimeBuilder day) {
        personalTimeOff.dayOffAt(day.buildDay());
    }

    protected void dayOffUsingOvertime(DateTimeBuilder day) {
        //this is a noop, just let the workday to its thing
    }

    protected void daysOffStarting(Supplier<DateTimeBuilder> start, Supplier<DateTimeBuilder> end) {
        personalTimeOff.consecutiveDaysOf(start.get().buildDay(), end.get().buildDay());
    }

    protected void dayOffSick(DateTimeBuilder day, String... multipleIgnoredFromTillsToKeepProperHistory) {
        sickLeave.dayOffAt(day.buildDay());
    }

    protected void daysOffSick(Supplier<DateTimeBuilder> start, Supplier<DateTimeBuilder> end, String... multipleIgnoredFromTillsToKeepProperHistory) {
        sickLeave.consecutiveDaysOf(start.get().buildDay(), end.get().buildDay());
    }

    private void attendedConference(DateTimeBuilder day) {
        conferenceDays.dayOffAt(day.buildDay());
    }

    private LocalDate startOfYear() {
        return new LocalDate(year, 1, 1);
    }

    private void year(DateTimeBuilder year) {
        january(year.january(), year.copy().february());
        february(year.february(), year.copy().march());
        march(year.march(), year.copy().april());
        april(year.april(), year.copy().may());
        may(year.may(), year.copy().june());
        june(year.june(), year.copy().july());
        july(year.july(), year.copy().august());
        august(year.august(), year.copy().september());
        september(year.september(), year.copy().october());
        october(year.october(), year.copy().november());
        november(year.november(), year.copy().december());
        december(year.december());
    }

    private LocalDate startThisYear() {
        return januaryFirst(year);
    }

    private LocalDate startOfNexYear() {
        return januaryFirst(year + 1);
    }

    private LocalDate januaryFirst(int year) {
        return new LocalDate(year, DateTimeConstants.JANUARY, 1);
    }

    private BalanceSheet balance(LocalDate start, LocalDate inclusiveEnd) {
        workLogBuilder.forInterval(new Interval(start.toDateTimeAtStartOfDay(inputTimeZone), inclusiveEnd.plusDays(1).toDateTimeAtStartOfDay(inputTimeZone)));
        year(DateTimeBuilder.Year(this.year));
        return balanceCalculator.balanceFor(workLogBuilder.timeCard());
    }

}
