package com.github.signed.timeless.workhours;

import org.joda.time.Duration;

public class WorkHoursPerDayBuilder {

    private Duration duration;
    private Duration toSubtract = Duration.ZERO;

    public WorkHoursPerDayBuilder hoursToWork(Duration duration) {
        this.duration = duration;
        return this;
    }

    public WorkHoursPerDayBuilder reduceByHalfAWorkDay(){
        toSubtract = toSubtract.plus(duration.dividedBy(2));
        return this;
    }

    public WorkHoursPerDayBuilder reduceByCompleteWorkDay(){
        return reduceByHalfAWorkDay().reduceByHalfAWorkDay();
    }

    public WorkHoursPerDay build(){
        Duration finalDuration = duration.minus(toSubtract);
        if (Duration.ZERO.isLongerThan(finalDuration)) {
            finalDuration = Duration.ZERO;
        }
        return WorkHoursPerDay.unreducedWorkHours(finalDuration);
    }
}
