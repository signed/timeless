package com.github.signed.timeless.storage;

import com.github.signed.timeless.Constants;
import com.github.signed.timeless.balance.BalanceCalculator;
import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.contract.EmployerCourtesy;
import com.github.signed.timeless.contract.WorkHours;
import com.github.signed.timeless.holidays.Holidays;
import com.github.signed.timeless.workhours.*;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkYear {

    private static Set<WorkHoursPerDayAdjuster> adjusters(WorkHoursPerDayAdjuster... adjusters){
        return new HashSet<>(Arrays.asList(adjusters));
    }

    private final DaysOffAdjuster personalTimeOff = new DaysOffAdjuster();
    private final DaysOffAdjuster sickLeave = new DaysOffAdjuster();
    private final DaysOffAdjuster conferenceDays = new DaysOffAdjuster();
    private final BalanceCalculator balanceCalculator;
    private final DateTimeZone inputTimeZone = DateTimeZone.getDefault();
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder().inLocalTime(inputTimeZone);
    private final int year;

    public WorkYear(int year){
        WorkHoursPerDayCompendium compendium = new WorkHoursPerDayCompendium(adjusters(personalTimeOff, new WorkHours(), new Holidays(), sickLeave, conferenceDays, new EmployerCourtesy()));
        balanceCalculator = new BalanceCalculator(compendium, Constants.frontendTimeZone());
        this.year = year;
    }

    public BalanceSheet balanceAtEndOfYear() {
        return balanceAtEndOfYearStarting(startThisYear());
    }

    public BalanceSheet balanceUntil(LocalDate until){
        LocalDate startOfYear = startOfYear();
        return balance(startOfYear, until);
    }

    public BalanceSheet balanceAtEndOfYearStarting(LocalDate start) {
        LocalDate endOfYear = startOfNexYear();

        return balance(start, endOfYear);
    }

    public BalanceSheet balanceUpUntilToday(){
        LocalDate today = new LocalDate();
        LocalDate startOfYear = startOfYear();
        LocalDate until = today;
        if (today.isBefore(startOfYear)){
            until = startOfYear;
        }
        return balance(startOfYear, until);
    }

    public BalanceSheet balanceFor(LocalDate start, LocalDate end) {
        if(start.isBefore(startOfYear())){
            start = startOfYear();
        }
        if(!end.isBefore(startOfNexYear())){
            end = startOfNexYear();
        }
        return balance(start, end);
    }

    public void january(DateTimeBuilder january) {
        //did not work
    }

    public void february(DateTimeBuilder february) {
        //did not work
    }

    public void march(DateTimeBuilder march) {
        //did not work
    }

    public void april(DateTimeBuilder april) {
        //did not work
    }

    public void may(DateTimeBuilder may) {
        //did not work
    }

    public void june(DateTimeBuilder june) {
        //did not work
    }

    public void july(DateTimeBuilder july) {
        //did not work
    }

    public void august(DateTimeBuilder august) {
        //did not work
    }

    public void september(DateTimeBuilder september) {
        //did not work
    }

    public void october(DateTimeBuilder october) {
        //did not work
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

    protected void halfADayOfOn(DateTimeBuilder day) {
        personalTimeOff.halfADayOffAt(day.buildDay());
    }

    protected void dayOfOn(DateTimeBuilder day) {
        personalTimeOff.dayOffAt(day.buildDay());
    }

    protected void dayOfUsingOvertime(DateTimeBuilder day){
        //this is a noop, just let the workday to its thing
    }

    protected void daysOffStarting(DateTimeBuilder start, DateTimeBuilder end) {
        personalTimeOff.consecutiveDaysOf(start.buildDay(), end.buildDay());
    }

    protected void wasSickOn(DateTimeBuilder day) {
        sickLeave.dayOffAt(day.buildDay());
    }

    private void visitedConference(DateTimeBuilder day) {
        conferenceDays.dayOffAt(day.buildDay());
    }

    private LocalDate startOfYear() {
        return new LocalDate(year, 1, 1);
    }

    private void year(DateTimeBuilder year) {
        january(year.january());
        february(year.february());
        march(year.march());
        april(year.april());
        may(year.may());
        june(year.june());
        july(year.july());
        august(year.august());
        september(year.september());
        october(year.october());
        november(year.november());
        december(year.december());
    }

    private LocalDate startThisYear() {
        return januaryFirst(year);
    }

    private LocalDate startOfNexYear() {
        return januaryFirst(year+1);
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
