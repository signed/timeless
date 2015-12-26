package com.github.signed.timeless.workhours;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import java6.util.Optional;
import java6.util.function.Consumer;

public class SickLeave implements WorkHoursPerDayAdjuster {

    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> sickDays = new HashMap<LocalDate, Consumer<WorkHoursPerDayBuilder>>();

    public void wasSickOn(LocalDate day) {
        sickDays.put(day, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByCompleteWorkDay();
            }
        });
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate day, final WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        mayBeSickAt(day).ifPresent(new Consumer<Consumer<WorkHoursPerDayBuilder>>() {
            @Override
            public void accept(Consumer<WorkHoursPerDayBuilder> consumer) {
                consumer.accept(workHoursPerDayBuilder);
            }
        });
    }

    private Optional<Consumer<WorkHoursPerDayBuilder>> mayBeSickAt(LocalDate day) {
        return Optional.ofNullable(sickDays.get(day));
    }

}
