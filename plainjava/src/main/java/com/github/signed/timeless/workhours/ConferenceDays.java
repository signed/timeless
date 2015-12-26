package com.github.signed.timeless.workhours;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import java6.util.Optional;
import java6.util.function.Consumer;

public class ConferenceDays implements WorkHoursPerDayAdjuster {

    private final Map<LocalDate, Consumer<WorkHoursPerDayBuilder>> conferenceDays = new HashMap<LocalDate, Consumer<WorkHoursPerDayBuilder>>();

    public void wasAtConference(LocalDate from, LocalDate until) {
        for (LocalDate day = from; !until.isBefore(day); day = day.plusDays(1)) {
            wasAtConference(day);
        }
    }

    public void wasAtConference(LocalDate day) {
        conferenceDays.put(day, new Consumer<WorkHoursPerDayBuilder>() {
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
        return Optional.ofNullable(conferenceDays.get(day));
    }
}
