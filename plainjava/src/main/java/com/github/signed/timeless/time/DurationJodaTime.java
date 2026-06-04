package com.github.signed.timeless.time;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DurationJodaTime implements Duration {
    private static final PeriodFormatter HourFormatter = new PeriodFormatterBuilder()
            .minimumPrintedDigits(2).printZeroNever().appendHours()
            .toFormatter();
    private static final PeriodFormatter MinuteFormatter = new PeriodFormatterBuilder()
            .minimumPrintedDigits(2)
            .printZeroIfSupported().appendMinutes()
            .toFormatter();
    private static final PeriodFormatter hoursWorkedFormatter = new PeriodFormatterBuilder().minimumPrintedDigits(2).printZeroIfSupported().appendHours().appendLiteral(":").appendMinutes().toFormatter();

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

    @Override
    public String asBalanceString() {
        final var absBalance = abs();
        final var period = absBalance.toPeriod();
        final var hour = period.toString(HourFormatter);
        final var minute = period.toString(MinuteFormatter);
        boolean isNegative = isShorterThan(Duration.ZERO());
        final var sign = isNegative ? "-" : "+";
        final var collect = Stream.of(hour, minute)
                .skip(hoursFor(absBalance))
                .collect(Collectors.joining(":"));
        return String.format("%s%5s", sign, collect);
    }

    @Override
    public String asHoursWorkedString() {
        final var period = toPeriod();
        return period.toString(hoursWorkedFormatter);
    }

    private static int hoursFor(Duration absBalance) {
        final var atLeastAnHour = !Duration.standardHours(1).isLongerThan(absBalance);
        return atLeastAnHour ? 0 : 1;
    }

    @Override
    public String toString() {
        return duration.toString();
    }
}
