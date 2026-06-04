package com.github.signed.timeless.contract;

import com.github.signed.timeless.time.DayOfWeek;
import com.github.signed.timeless.time.Duration;
import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

import java.util.HashMap;
import java.util.Map;


public class WeeklyWorkHours implements WorkHoursPerDayAdjuster {

    private record WeeklyWorkSchedule(Map<DayOfWeek, Duration> schedule) {
        public Duration hoursToWorkAt(DayOfWeek dayOfWeekConstant) {
            return schedule.get(dayOfWeekConstant);
        }
    }

    public static WeeklyWorkHours fortyHourWeek() {
        return fiveDayWorkWeekOf(40);
    }

    public static WeeklyWorkHours fiveDayWorkWeekOf(int hours) {
        return createWeeklyWorkHours(hours, 5);
    }

    public static WeeklyWorkHours sevenDayWorkWeekOf(int hours) {
        return createWeeklyWorkHours(hours, 7);
    }

    private static WeeklyWorkHours createWeeklyWorkHours(int hours, int workdaysPerWeek) {
        var hoursToWorkDaily = Duration.standardHours(hours).dividedBy(workdaysPerWeek);
        var weeklyWorkSchedule = weekendsAreFreeDailyHours(hoursToWorkDaily);
        return new WeeklyWorkHours(weeklyWorkSchedule);
    }

    private static WeeklyWorkSchedule weekendsAreFreeDailyHours(final Duration dailyWorkHours) {
        var internal = new HashMap<DayOfWeek, Duration>();
        internal.put(DayOfWeek.Monday, dailyWorkHours);
        internal.put(DayOfWeek.Tuesday, dailyWorkHours);
        internal.put(DayOfWeek.Wednesday, dailyWorkHours);
        internal.put(DayOfWeek.Thursday, dailyWorkHours);
        internal.put(DayOfWeek.Friday, dailyWorkHours);
        internal.put(DayOfWeek.Saturday, Duration.ZERO());
        internal.put(DayOfWeek.Sunday, Duration.ZERO());
        return new WeeklyWorkSchedule(internal);
    }

    private final WeeklyWorkSchedule weeklyWorkSchedule;

    private WeeklyWorkHours(final WeeklyWorkSchedule weeklyWorkSchedule) {
        this.weeklyWorkSchedule = weeklyWorkSchedule;
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        workHoursPerDayBuilder.hoursToWork(hoursToWorkAt(date));
    }

    private Duration hoursToWorkAt(LocalDate day) {
        return weeklyWorkSchedule.hoursToWorkAt(day.dayOfWeek());
    }
}
