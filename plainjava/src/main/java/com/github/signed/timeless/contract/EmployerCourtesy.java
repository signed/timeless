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

    public static List<SpecialDay> sampleSpecialDays() {
        var christmas = christmas();
        var newYearsEve = newYearsEve();
        return Arrays.asList(christmas, newYearsEve);
    }

    private final WorkHoursAdjuster adjuster;
    private final List<SpecialDay> specialDays;

    public EmployerCourtesy() {
        adjuster = WorkHoursPerDayBuilder::reduceByHalfAWorkDay;
        specialDays = sampleSpecialDays();
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        final var flup = specialDays.stream().anyMatch(it -> it.test(date));
        if (flup) {
            adjuster.accept(workHoursPerDayBuilder);
        }
    }
}
