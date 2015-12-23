package com.github.signed.timeless.workhours;

import org.joda.time.Duration;

public class WorkHoursPerDayBuilder {

    private Duration duration;
    private long percentageToSubtract = 0L;

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

    public WorkHoursPerDay build() {
        if(percentageToSubtract >= 100){
            return WorkHoursPerDay.unreducedWorkHours(Duration.ZERO);
        }
        Duration toSubtract = this.duration.dividedBy(100).multipliedBy(percentageToSubtract);
        return WorkHoursPerDay.unreducedWorkHours(this.duration.minus(toSubtract));
    }
}
