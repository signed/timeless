package com.github.signed.timeless.balance;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.workhours.WorkHoursPerDay;

public class BalanceRow implements Comparable<BalanceRow>{
    private final LocalDate day;
    private final WorkHoursPerDay workHoursPerDay;
    private final DailyWorkLog dailyWorkLog;

    public BalanceRow(LocalDate day, WorkHoursPerDay workHoursPerDay, DailyWorkLog dailyWorkLog) {
        this.day = day;
        this.workHoursPerDay = workHoursPerDay;
        this.dailyWorkLog = dailyWorkLog;
    }

    public Duration balance(){
        return dailyWorkLog.timeWorked().minus(workHoursPerDay.duration());
    }

    public LocalDate day(){
        return day;
    }

    @Override
    public int compareTo(BalanceRow o) {
        return day.compareTo(o.day);
    }
}
