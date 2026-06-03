package com.github.signed.timeless;

import com.github.signed.timeless.time.Interval;

public class ConsecutiveTime {

    private final Interval interval;

    ConsecutiveTime(Punch start, Punch stop) {
        this.interval = Interval.of(start.dateTime(), stop.dateTime());
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
