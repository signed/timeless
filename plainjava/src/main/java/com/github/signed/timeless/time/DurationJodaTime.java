package com.github.signed.timeless.time;

import org.joda.time.Period;

public class DurationJodaTime implements Duration {
    private final org.joda.time.Duration duration;

    public DurationJodaTime(org.joda.time.Duration duration) {
        this.duration = duration;
    }

    @Override
    public Duration dividedBy(int divisor) {
        return new DurationJodaTime(duration.dividedBy(divisor));
    }

    @Override
    public Duration multipliedBy(long multiplicand) {
        return new DurationJodaTime(duration.multipliedBy(multiplicand));
    }

    @Override
    public Duration minus(Duration amount) {
        return new DurationJodaTime(duration.minus(amount.toJoda()));
    }

    @Override
    public org.joda.time.Duration toJoda() {
        return duration;
    }

    @Override
    public Period toPeriod() {
        return duration.toPeriod();
    }

    @Override
    public Duration abs() {
        return new DurationJodaTime(duration.abs());
    }

    @Override
    public boolean isShorterThan(Duration other) {
        return duration.isShorterThan(other.toJoda());
    }

    @Override
    public boolean isLongerThan(Duration other) {
        return duration.isLongerThan(other.toJoda());
    }

    @Override
    public Duration plus(Duration amount) {
        return new DurationJodaTime(this.duration.plus(amount.toJoda()));
    }

    @Override
    public Duration negated() {
        return new DurationJodaTime(this.duration.negated());
    }

    @Override
    public int compareTo(Duration other) {
        return duration.compareTo(other.toJoda());
    }

    @Override
    public int hashCode() {
        return duration.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DurationJodaTime jodaTime) {
            return duration.equals(jodaTime.toJoda());
        }
        return false;
    }

}
