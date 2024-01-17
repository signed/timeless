package com.github.signed.timeless.workhours;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import java6.util.Optional;
import java6.util.function.Consumer;

public class PersonalTimeOff implements WorkHoursPerDayAdjuster {

    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> daysOff = new HashMap<LocalDate, Consumer<WorkHoursPerDayBuilder>>();

    public void consecutiveDaysOf(LocalDate from, LocalDate until) {
        for (LocalDate day = from; !until.isBefore(day); day = day.plusDays(1)) {
            dayOffAt(day);
        }
    }

    public void dayOffAt(LocalDate date) {
        daysOff.put(date, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByCompleteWorkDay();
            }
        });
    }

    public void halfADayOffAt(LocalDate workday) {
        daysOff.put(workday, new Consumer<WorkHoursPerDayBuilder>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByHalfAWorkDay();
            }
        });
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate day, final WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        mayBeDayOffAt(day).ifPresent(new Consumer<Consumer<WorkHoursPerDayBuilder>>() {
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
