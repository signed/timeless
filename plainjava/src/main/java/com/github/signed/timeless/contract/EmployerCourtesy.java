package com.github.signed.timeless.contract;

import com.github.signed.timeless.specialdays.SpecialDay;
import com.github.signed.timeless.workhours.WorkHoursAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.List;

import static com.github.signed.timeless.specialdays.SpecialDays.christmas;
import static com.github.signed.timeless.specialdays.SpecialDays.newYearsEve;

public class EmployerCourtesy implements WorkHoursPerDayAdjuster {

    public static EmployerCourtesy halfDayOffOn(SpecialDay ...days) {
        return new EmployerCourtesy(Arrays.asList(days), WorkHoursPerDayBuilder::reduceByHalfAWorkDay);
    }

    private final List<SpecialDay> days;
    private final WorkHoursAdjuster adjuster;

    private EmployerCourtesy(final List<SpecialDay> days, final WorkHoursAdjuster adjuster) {
        this.days = days;
        this.adjuster = adjuster;
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        final var flup = days.stream().anyMatch(it -> it.test(date));
        if (flup) {
            adjuster.accept(workHoursPerDayBuilder);
        }
    }
}
