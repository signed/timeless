package com.github.signed.timeless.balance;

import static java8.util.stream.StreamSupport.stream;

import java.util.Collections;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.ConsecutiveTime;

import java8.util.function.BinaryOperator;
import java8.util.function.Function;

class DailyWorkLog {

    private final LocalDate day;
    private final List<ConsecutiveTime> consecutiveTimes;

    DailyWorkLog(LocalDate day, DateTimeZone dateTimeZone, List<ConsecutiveTime> consecutiveTimes) {
        this.day = day;
        this.consecutiveTimes = consecutiveTimes;
    }

    List<ConsecutiveTime> consecutiveTimes(){
        return Collections.unmodifiableList(consecutiveTimes);
    }

    Duration timeWorked() {

        return stream(consecutiveTimes).map(new Function<ConsecutiveTime, Duration>() {
            @Override
            public Duration apply(ConsecutiveTime consecutiveTime) {
                return consecutiveTime.toDuration();
            }
        }).reduce(Duration.ZERO, new BinaryOperator<Duration>() {
            @Override
            public Duration apply(Duration accumulator, Duration it) {
                return accumulator.plus(it);
            }
        });

    }

}
