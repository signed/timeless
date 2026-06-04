package com.github.signed.timeless.time;

public interface Duration extends Comparable<Duration> {
    static Duration ZERO() {
        return TimeSource.instance.durationZero();
    }

    static Duration standardMinutes(int minutes) {
        return TimeSource.instance.durationMinutes(minutes);
    }

    static Duration standardHours(int hours) {
        return TimeSource.instance.durationHours(hours);
    }

    static Duration standardDays(int days) {
        return TimeSource.instance.durationDays(days);
    }

    Duration dividedBy(int divisor);

    Duration multipliedBy(long multiplicand);

    Duration minus(Duration toSubtract);

    org.joda.time.Period toPeriod();

    Duration abs();

    boolean isShorterThan(Duration other);

    boolean isLongerThan(Duration other);

    Duration plus(Duration amount);

    Duration negated();

    String asBalanceString();

    String asHoursWorkedString();
}
