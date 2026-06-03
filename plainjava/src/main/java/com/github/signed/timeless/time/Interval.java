package com.github.signed.timeless.time;

import org.joda.time.Duration;
import org.joda.time.ReadableInterval;

public interface Interval {
    static Interval of(DateTime start, DateTime end) {
        return new IntervalJodaTime(new org.joda.time.Interval(start.toJoda(), end.toJoda()));
    }

    static Interval infinity() {
        return new IntervalJodaTime(new org.joda.time.Interval(Long.MIN_VALUE, Long.MAX_VALUE));
    }

    boolean contains(DateTime dateTimeAtStartOfDay);

    DateTime getStart();

    DateTime getEnd();

    Duration toDuration();

    Interval overlap(Interval interval);

    boolean overlaps(Interval interval);

    ReadableInterval toJoda();
}
