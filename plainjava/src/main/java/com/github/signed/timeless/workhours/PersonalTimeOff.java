package com.github.signed.timeless.workhours;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import java6.util.Optional;
import java6.util.function.Consumer;

public class PersonalTimeOff implements WorkHoursPerDayAdjuster {
    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> daysOf = new HashMap<LocalDate, Consumer<WorkHoursPerDayBuilder>>();

    public void timeOff(LocalDate from, LocalDate until) {
        for (LocalDate day = from; !until.isBefore(day); day = day.plusDays(1)) {
            dayOffAt(day);
        }
    }

    public void dayOffAt(LocalDate day) {
        daysOf.put(day, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByCompleteWorkDay();
            }
        });
    }

    public void halfADayOffAt(LocalDate workday) {
        daysOf.put(workday, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByHalfAWorkDay();
            }
        });
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate day, final WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        maybeDayOff(day).ifPresent(new Consumer<Consumer<WorkHoursPerDayBuilder>>() {
            @Override
            public void accept(Consumer<WorkHoursPerDayBuilder> consumer) {
                consumer.accept(workHoursPerDayBuilder);
            }
        });
    }

    private Optional<Consumer<WorkHoursPerDayBuilder>> maybeDayOff(LocalDate day) {
        return Optional.ofNullable(daysOf.get(day));
    }
}
