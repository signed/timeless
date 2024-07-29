package com.github.signed.timeless.workhours;


import org.joda.time.Duration;

public class WorkHoursPerDay {

    private final Duration duration;

    private WorkHoursPerDay(Duration duration) {
        this.duration = duration;
    }

    public static WorkHoursPerDay freeDay() {
        return unreducedWorkHours(Duration.ZERO);
    }

    public static WorkHoursPerDay unreducedWorkHours(Duration duration) {
        return new WorkHoursPerDay(duration);
    }

    public Duration duration() {
        return duration;
    }
}
