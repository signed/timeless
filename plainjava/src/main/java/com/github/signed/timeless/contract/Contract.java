package com.github.signed.timeless.contract;

import com.github.signed.timeless.holidays.Holidays;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.List;

public class Contract implements WorkHoursPerDayAdjuster {

    public static Contract sampleContract() {
        // actual holidays depend on your place of work that is specified in the contract
        return new Contract(Arrays.asList(new WorkHours(), new EmployerCourtesy(), new Holidays()));
    }

    private final List<WorkHoursPerDayAdjuster> adjusters;

    public Contract(final List<WorkHoursPerDayAdjuster> adjusters) {
        this.adjusters = adjusters;
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        adjusters.forEach(adjuster -> adjuster.adjustHoursToWorkFor(date, workHoursPerDayBuilder));
    }

}
