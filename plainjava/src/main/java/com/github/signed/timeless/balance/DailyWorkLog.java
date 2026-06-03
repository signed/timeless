package com.github.signed.timeless.balance;

import com.github.signed.timeless.ConsecutiveTime;
import com.github.signed.timeless.time.LocalDate;
import java8.util.function.BinaryOperator;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.Stream;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import com.github.signed.timeless.time.Interval;


import java.util.List;

import static java8.util.stream.StreamSupport.stream;

class DailyWorkLog {

    private final List<ConsecutiveTime> consecutiveTimes;
    private final Interval thisWorkDay;

    DailyWorkLog(LocalDate day, DateTimeZone dateTimeZone, List<ConsecutiveTime> consecutiveTimes) {
        DateTime start = day.toDateTimeAtStartOfDay(dateTimeZone);
        DateTime end = start.plusDays(1);
        this.consecutiveTimes = consecutiveTimes;
        this.thisWorkDay = Interval.of(start, end);
    }

    List<Interval> intervalsWorked() {
        return adjusted().collect(Collectors.<Interval>toList());
    }

    Duration timeWorked() {
        return adjusted().map(new Function<Interval, Duration>() {
            @Override
            public Duration apply(Interval consecutiveTime) {
                return consecutiveTime.toDuration();
            }
        }).reduce(Duration.ZERO, new BinaryOperator<>() {
            @Override
            public Duration apply(Duration accumulator, Duration it) {
                return accumulator.plus(it);
            }
        });
    }

    private Stream<Interval> adjusted() {
        return stream(consecutiveTimes).map(new Function<>() {
            @Override
            public Interval apply(ConsecutiveTime consecutiveTime) {
                return consecutiveTime.overlap(thisWorkDay);
            }
        });
    }

}
