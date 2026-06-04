package com.github.signed.timeless.time;

public interface Duration extends Comparable<Duration> {
    static Duration ZERO() {
        return new DurationJodaTime(org.joda.time.Duration.ZERO);
    }

    static Duration standardMinutes(int i) {
        return new DurationJodaTime(org.joda.time.Duration.standardMinutes(i));
    }

    static Duration standardHours(int hours) {
        return new DurationJodaTime(org.joda.time.Duration.standardHours(hours));
    }

    static Duration standardDays(int i) {
        return new DurationJodaTime(org.joda.time.Duration.standardDays(i));
    }

    Duration dividedBy(int divisor);

    Duration multipliedBy(long multiplicand);

    Duration minus(Duration toSubtract);

    org.joda.time.Duration toJoda();

    org.joda.time.Period toPeriod();

    Duration abs();

    boolean isShorterThan(Duration other);

    boolean isLongerThan(Duration other);

    Duration plus(Duration amount);

    Duration negated();
}
