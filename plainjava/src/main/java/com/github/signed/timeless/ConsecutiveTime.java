package com.github.signed.timeless;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

public class ConsecutiveTime {

    private final Punch start;
    private final Punch stop;

    ConsecutiveTime(Punch start, Punch stop) {
        this.start = start;
        this.stop = stop;
    }

    public DateTime start() {
        return start.dateTime();
    }

    public DateTime stop() {
        return stop.dateTime();
    }

    public Duration toDuration() {
        return new Interval(start.dateTime(), stop.dateTime()).toDuration().toDuration();
    }
}
