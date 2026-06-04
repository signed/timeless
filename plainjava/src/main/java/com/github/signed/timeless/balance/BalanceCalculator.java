package com.github.signed.timeless.balance;

import com.github.signed.timeless.ConsecutiveTime;
import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.TimeCard;
import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.Interval;
import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.workhours.WorkHoursPerDay;

import java.util.ArrayList;
import java.util.List;

public class BalanceCalculator {

    private final HoursRequired hoursRequired;
    private final DateTimeZone dateTimeZone;

    public BalanceCalculator(HoursRequired hoursRequired, DateTimeZone dateTimeZone) {
        this.hoursRequired = hoursRequired;
        this.dateTimeZone = dateTimeZone;
    }

    public BalanceSheet balanceFor(TimeCard timeCard) {
        List<BalanceRow> balanceRows = new ArrayList<>();
        for (LocalDate day = timeCard.from(); timeCard.covers(day); day = day.plusDays(1)) {
            balanceRows.add(balanceRowFor(day, timeCard));
        }
        return new BalanceSheet(balanceRows);
    }

    private BalanceRow balanceRowFor(LocalDate day, TimeCard timeCard) {
        WorkHoursPerDay workHoursPerDay = this.hoursRequired.hoursToWorkAt(day);
        DailyWorkLog dailyWorkLog = new DailyWorkLog(day, dateTimeZone, worked(timeCard, day));
        return new BalanceRow(day, workHoursPerDay, dailyWorkLog);
    }

    private List<ConsecutiveTime> worked(TimeCard timeCard, LocalDate day) {
        var start = day.toDateTimeAtStartOfDay(dateTimeZone);
        var end = day.plusDays(1).toDateTimeAtStartOfDay(dateTimeZone);
        Interval workday = Interval.of(start, end);
        return timeCard.consecutiveTimesOverlapping(workday);
    }

}
