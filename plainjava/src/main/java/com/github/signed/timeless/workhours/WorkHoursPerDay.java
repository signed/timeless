package com.github.signed.timeless.workhours;


import org.joda.time.Duration;

public class WorkHoursPerDay {

    public static WorkHoursPerDay freeDay() {
        return unreducedWorkHours(Duration.ZERO);
    }

    public static WorkHoursPerDay unreducedWorkHours(Duration initialDuration) {
        return new WorkHoursPerDay(initialDuration);
    }

    private final Duration reducedDuration;

    private WorkHoursPerDay(Duration reducedDuration) {
        this.reducedDuration = reducedDuration;
    }

    public Duration duration(){
        return reducedDuration;
    }
}
