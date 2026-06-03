package com.github.signed.timeless.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.ReadableInterval;

public class IntervalJodaTime implements Interval {
    private final org.joda.time.Interval interval;

    public IntervalJodaTime(org.joda.time.Interval interval) {
        this.interval = interval;
    }

    @Override
    public boolean contains(DateTime dateTimeAtStartOfDay) {
        return interval.contains(dateTimeAtStartOfDay);
    }

    @Override
    public DateTime getStart() {
        return interval.getStart();
    }

    @Override
    public DateTime getEnd() {
        return interval.getEnd();
    }

    @Override
    public Duration toDuration() {
        return interval.toDuration();
    }

    @Override
    public Interval overlap(Interval other) {
        return new IntervalJodaTime(interval.overlap(other.toJoda()));
    }

    @Override
    public boolean overlaps(Interval other) {
        return interval.overlaps(other.toJoda());
    }

    @Override
    public ReadableInterval toJoda() {
        return interval;
    }
}
