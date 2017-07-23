package com.github.signed.timeless;

import org.joda.time.Interval;

public class ConsecutiveTime {

    private final Interval interval;

    ConsecutiveTime(Punch start, Punch stop) {
        this.interval = new Interval(start.dateTime(), stop.dateTime());
    }

    public Interval overlap(Interval interval) {
        return interval().overlap(interval);
    }

    boolean overlapsWith(Interval interval) {
        return interval().overlaps(interval);
    }

    private Interval interval() {
        return interval;
    }
}
