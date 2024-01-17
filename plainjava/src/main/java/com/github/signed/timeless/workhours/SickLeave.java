package com.github.signed.timeless.workhours;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import java6.util.Optional;
import java6.util.function.Consumer;

public class SickLeave implements WorkHoursPerDayAdjuster {

    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> daysOff = new HashMap<LocalDate, Consumer<WorkHoursPerDayBuilder>>();

    public void dayOffAt(LocalDate date) {
        daysOff.put(date, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByCompleteWorkDay();
            }
        });
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, final WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        mayBeDayOffAt(date).ifPresent(new Consumer<Consumer<WorkHoursPerDayBuilder>>() {
            @Override
            public void accept(Consumer<WorkHoursPerDayBuilder> consumer) {
                consumer.accept(workHoursPerDayBuilder);
            }
        });
    }

    private Optional<Consumer<WorkHoursPerDayBuilder>> mayBeDayOffAt(LocalDate day) {
        return Optional.ofNullable(daysOff.get(day));
    }

}
