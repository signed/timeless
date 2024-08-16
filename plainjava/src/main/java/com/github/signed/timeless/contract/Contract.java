package com.github.signed.timeless.contract;

import com.github.signed.timeless.Constants;
import com.github.signed.timeless.holidays.Holidays;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.List;

public class Contract implements WorkHoursPerDayAdjuster {

    public static Contract sampleContract() {
        // actual holidays depend on your place of work that is specified in the contract
        return new Contract(infinity(), Arrays.asList(new WorkHours(), new EmployerCourtesy(), new Holidays()));
    }

    private static Interval fromTill(LocalDate firstDay, LocalDate lastDay, DateTimeZone zone) {
        final var start = firstDay.toDateTimeAtStartOfDay(zone);
        final var end = lastDay.plusDays(1).toDateTimeAtStartOfDay(zone);
        return new Interval(start, end);
    }

    public static Interval infinity() {
        return new Interval(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private final Interval term;
    private final List<WorkHoursPerDayAdjuster> adjusters;

    public Contract(final Interval term, final List<WorkHoursPerDayAdjuster> adjusters) {
        this.term = term;
        this.adjusters = adjusters;
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        adjusters.forEach(adjuster -> adjuster.adjustHoursToWorkFor(date, workHoursPerDayBuilder));
    }

    public boolean active(LocalDate date) {
        return this.term.contains(date.toDateTimeAtStartOfDay(Constants.frontendTimeZone()));
    }
}
