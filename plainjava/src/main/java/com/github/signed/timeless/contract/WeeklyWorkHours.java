package com.github.signed.timeless.contract;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

import static org.joda.time.DateTimeConstants.FRIDAY;
import static org.joda.time.DateTimeConstants.MONDAY;
import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;
import static org.joda.time.DateTimeConstants.THURSDAY;
import static org.joda.time.DateTimeConstants.TUESDAY;
import static org.joda.time.DateTimeConstants.WEDNESDAY;
import static org.joda.time.Duration.ZERO;

public class WeeklyWorkHours implements WorkHoursPerDayAdjuster {

    record WeeklyWorkSchedule(Map<Integer, Duration> schedule) {
        public Duration hoursToWorkAt(int dayOfWeekConstant) {
            return schedule.get(dayOfWeekConstant);
        }
    }

    public static WeeklyWorkHours fortyHourWeek() {
        return fiveDayWorkWeekOf(40);
    }

    public static WeeklyWorkHours fiveDayWorkWeekOf(int hours) {
        var hoursToWorkDaily = Duration.standardHours(hours).dividedBy(5);
        var weeklyWorkSchedule = weekendsAreFreeDailyHours(hoursToWorkDaily);
        return new WeeklyWorkHours(weeklyWorkSchedule);
    }

    private static WeeklyWorkSchedule weekendsAreFreeDailyHours(final Duration dailyWorkHours) {
        var internal = new HashMap<Integer, Duration>();
        internal.put(MONDAY, dailyWorkHours);
        internal.put(TUESDAY, dailyWorkHours);
        internal.put(WEDNESDAY, dailyWorkHours);
        internal.put(THURSDAY, dailyWorkHours);
        internal.put(FRIDAY, dailyWorkHours);
        internal.put(SATURDAY, ZERO);
        internal.put(SUNDAY, ZERO);
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
        return weeklyWorkSchedule.hoursToWorkAt(day.dayOfWeek().get());
    }
}
