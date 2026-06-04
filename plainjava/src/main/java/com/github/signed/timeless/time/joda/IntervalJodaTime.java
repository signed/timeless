package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.DateTime;
import com.github.signed.timeless.time.Duration;
import com.github.signed.timeless.time.Interval;

import static com.github.signed.timeless.time.joda.DateTimeJodaTime.jodaDateTimeIn;

public class IntervalJodaTime implements Interval {
    private final org.joda.time.Interval interval;

    public IntervalJodaTime(org.joda.time.Interval interval) {
        this.interval = interval;
    }

    @Override
    public boolean contains(DateTime dateTime) {
        return interval.contains(jodaDateTimeIn(dateTime));
    }

    @Override
    public DateTime getStart() {
        return new DateTimeJodaTime(interval.getStart());
    }

    @Override
    public DateTime getEnd() {
        return new DateTimeJodaTime(interval.getEnd());
    }

    @Override
    public Duration toDuration() {
        return new DurationJodaTime(interval.toDuration());
    }

    @Override
    public Interval overlap(Interval other) {
        return new IntervalJodaTime(this.interval.overlap(intervalIn(other)));
    }

    @Override
    public boolean overlaps(Interval other) {
        return interval.overlaps(intervalIn(other));
    }

    private org.joda.time.Interval intervalIn(Interval other) {
        if (other instanceof IntervalJodaTime joda) {
            return joda.interval;
        }
        throw new IllegalArgumentException();
    }
}
