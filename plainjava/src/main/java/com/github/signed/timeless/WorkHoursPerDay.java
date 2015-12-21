package com.github.signed.timeless;


import org.joda.time.Duration;

public class WorkHoursPerDay {

    public static WorkHoursPerDay unreducedWorkHours(Duration initialDuration) {
        return new WorkHoursPerDay(initialDuration, initialDuration);
    }

    private final Duration initialDuration;
    private final Duration reducedDuration;

    private WorkHoursPerDay(Duration initialDuration, Duration reducedDuration) {
        this.initialDuration = initialDuration;
        this.reducedDuration = reducedDuration;
    }

    public Duration duration(){
        return reducedDuration;
    }
}
