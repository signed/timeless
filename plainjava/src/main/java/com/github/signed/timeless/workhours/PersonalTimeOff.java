package com.github.signed.timeless.workhours;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import java6.util.function.Consumer;

public class PersonalTimeOff implements WorkHoursPerDayAdjuster {
    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> daysOf = new HashMap<LocalDate, Consumer<WorkHoursPerDayBuilder>>();

    public void dayOfAt(LocalDate day) {
        daysOf.put(day, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByCompleteWorkDay();
            }
        });
    }

    public void halfADayOfAt(LocalDate workday) {
        daysOf.put(workday, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByHalfAWorkDay();
            }
        });
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate day, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        if (daysOf.get(day) != null) {
            daysOf.get(day).accept(workHoursPerDayBuilder);
        }
    }
}
