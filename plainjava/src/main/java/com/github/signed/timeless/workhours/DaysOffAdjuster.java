package com.github.signed.timeless.workhours;

import java6.util.Optional;
import java6.util.function.Consumer;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class DaysOffAdjuster implements WorkHoursPerDayAdjuster {

    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> daysOff = new HashMap<>();

    public void consecutiveDaysOf(LocalDate from, LocalDate until) {
        for (LocalDate day = from; !until.isBefore(day); day = day.plusDays(1)) {
            dayOffAt(day);
        }
    }

    public void dayOffAt(LocalDate date) {
        daysOff.put(date, new Consumer<>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByCompleteWorkDay();
            }
        });
    }

    public void halfADayOffAt(LocalDate workday) {
        daysOff.put(workday, new Consumer<>() {
            @Override
            public void accept(WorkHoursPerDayBuilder workHoursPerDayBuilder) {
                workHoursPerDayBuilder.reduceByHalfAWorkDay();
            }
        });
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, final WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        mayBeDayOffAt(date).ifPresent(new Consumer<>() {
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
