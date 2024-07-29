package com.github.signed.timeless.workhours;

import com.github.signed.timeless.holidays.Holiday;
import java6.util.Optional;
import org.joda.time.Duration;

public class WorkHoursPerDayBuilder {

    private Duration duration;
    private long percentageToSubtract = 0L;
    private Optional<Holiday> maybeHoliday = Optional.empty();

    public WorkHoursPerDayBuilder hoursToWork(Duration duration) {
        this.duration = duration;
        return this;
    }

    public WorkHoursPerDayBuilder reduceByHalfAWorkDay() {
        percentageToSubtract += 50;
        return this;
    }

    public WorkHoursPerDayBuilder reduceByCompleteWorkDay() {
        return reduceByHalfAWorkDay().reduceByHalfAWorkDay();
    }

    public WorkHoursPerDayBuilder holiday(Holiday holiday) {
        maybeHoliday = Optional.of(holiday);
        return this;
    }

    public WorkHoursPerDay build() {
        if (percentageToSubtract >= 100 || maybeHoliday.isPresent()) {
            return WorkHoursPerDay.freeDay();
        }
        Duration toSubtract = this.duration.dividedBy(100).multipliedBy(percentageToSubtract);
        return WorkHoursPerDay.unreducedWorkHours(this.duration.minus(toSubtract));
    }

}
