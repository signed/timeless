package com.github.signed.timeless.storage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import com.github.signed.timeless.balance.BalanceCalculator;
import com.github.signed.timeless.balance.BalanceSheet;
import com.github.signed.timeless.contract.EmployerCourtesy;
import com.github.signed.timeless.contract.WorkHours;
import com.github.signed.timeless.workhours.ConferenceDays;
import com.github.signed.timeless.workhours.Holidays;
import com.github.signed.timeless.workhours.PersonalTimeOff;
import com.github.signed.timeless.workhours.SickLeave;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayCompendium;

public class WorkYear {


    private static Set<WorkHoursPerDayAdjuster> adjusters(WorkHoursPerDayAdjuster... adjusters){
        return new HashSet<WorkHoursPerDayAdjuster>(Arrays.asList(adjusters));
    }

    private final PersonalTimeOff timeOff = new PersonalTimeOff();
    private final SickLeave sickLeave = new SickLeave();
    private final ConferenceDays conferenceDays = new ConferenceDays();
    private final BalanceCalculator balanceCalculator;
    private final DateTimeZone inputTimeZone = DateTimeZone.getDefault();
    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder().inLocalTime(inputTimeZone);
    private final int year;

    public WorkYear(Duration initialBalance, int year){
        WorkHoursPerDayCompendium compendium = new WorkHoursPerDayCompendium(adjusters(timeOff, new WorkHours(), new Holidays(), sickLeave, conferenceDays, new EmployerCourtesy()));
        balanceCalculator = new BalanceCalculator(initialBalance, compendium);
        this.year = year;
    }

    public BalanceSheet balanceTillEndOfYearStarting(LocalDate start) {
        LocalDate endOfYear = new LocalDate(2016, DateTimeConstants.JANUARY, 1);

        return balance(start, endOfYear);
    }

    public BalanceSheet balanceUpUntilToday(){
        LocalDate today = new LocalDate();
        LocalDate startOfYear = new LocalDate(year, 1, 1);
        LocalDate until = today;
        if (today.isBefore(startOfYear)){
            until = startOfYear;
        }
        return balance(startOfYear, until);
    }

    private BalanceSheet balance(LocalDate start, LocalDate endOfYear) {
        workLogBuilder.forInterval(new Interval(start.toDateTimeAtStartOfDay(inputTimeZone), endOfYear.toDateTimeAtStartOfDay(inputTimeZone)));
        year(DateTimeBuilder.Year(this.year));
        return balanceCalculator.balanceFor(workLogBuilder.timeCard());
    }

    private void year(DateTimeBuilder _2015) {
        january(_2015.january());
        february(_2015.february());
        march(_2015.march());
        april(_2015.april());
        may(_2015.may());
        june(_2015.june());
        july(_2015.july());
        august(_2015.august());
        september(_2015.september());
        october(_2015.october());
        november(_2015.november());
        december(_2015.december());
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

    public void may(DateTimeBuilder may) {
        //did not work
    }

    public void july(DateTimeBuilder july) {
        //did not work
    }

    public void april(DateTimeBuilder april) {
        //did not work
    }

    public void june(DateTimeBuilder june) {
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

    protected void visitedXpDays(DateTimeBuilder day){
        visitedConference(day);
    }

    protected void visitedAwesomeDay(DateTimeBuilder day){
        visitedConference(day);
    }

    protected void visitedSocratesOn(DateTimeBuilder day) {
        visitedConference(day);
    }

    protected void visitedConference(DateTimeBuilder day) {
        conferenceDays.wasAtConference(day.buildDay());
    }

    protected void halfADayOfOn(DateTimeBuilder day) {
        timeOff.halfADayOffAt(day.buildDay());
    }

    protected void dayOfOn(DateTimeBuilder day) {
        timeOff.dayOffAt(day.buildDay());
    }

    protected void daysOffStarting(DateTimeBuilder start, DateTimeBuilder end) {
        timeOff.timeOff(start.buildDay(), end.buildDay());
    }

    protected void wasSickOn(DateTimeBuilder day) {
        sickLeave.wasSickOn(day.buildDay());
    }
}
