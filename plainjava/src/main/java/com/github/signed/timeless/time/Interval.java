package com.github.signed.timeless.time;

public interface Interval {
    static Interval of(DateTime start, DateTime end) {
        return TimeSource.instance.intervalOf(start, end);
    }

    static Interval infinity() {
        return TimeSource.instance.intervalInfinity();
    }

    boolean contains(DateTime dateTimeAtStartOfDay);

    DateTime getStart();

    DateTime getEnd();

    Duration toDuration();

    Interval overlap(Interval interval);

    boolean overlaps(Interval interval);
}
