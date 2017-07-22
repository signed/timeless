package com.github.signed.timeless.storage;

import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import com.github.signed.timeless.Punch;
import com.github.signed.timeless.TimeCard;

import java6.util.Optional;
import java6.util.function.Supplier;

public class WorkLogBuilder {

    private final PunchesBuilder punches = new PunchesBuilder();
    private DateTimeZone inputTimeZone = DateTimeZone.UTC;
    private DateTimeBuilder day;
    private Optional<Interval> maybeInterval = Optional.empty();

    public WorkLogBuilder on(LocalDate localDate) {
        return on(new DateTimeBuilder().on(localDate));
    }

    public WorkLogBuilder on(DateTimeBuilder day) {
        this.day = day;
        return this;
    }

    public WorkLogBuilder inLocalTime() {
        return inLocalTime(DateTimeZone.getDefault());
    }

    public WorkLogBuilder inLocalTime(DateTimeZone inputTimeZone) {
        this.inputTimeZone = inputTimeZone;
        return this;
    }

    public WorkLogBuilder workedInSpareTime(String... multipleFromTills) {
        return workedFrom(multipleFromTills);
    }

    public WorkLogBuilder teamBuilding(String occasion, String... multipleFromTills) {
        //ignore right now
        return this;
    }

    public WorkLogBuilder workedDoubleTimeFrom(String... multipleFromTills) {
        return this.workedFrom(multipleFromTills);
    }

    public WorkLogBuilder workedFrom(String... multipleFromTills) {
        for (String fromTill : multipleFromTills) {
            String[] split = fromTill.split("-");
            HourAndMinute in = HourAndMinute.parse(split[0]);
            HourAndMinute out = HourAndMinute.parse(split[1]);
            punches.punchInAt(day().at(in));
            punches.punchOutAt(day().nextDay().at(out));
        }
        return this;
    }

    public WorkLogBuilder didNotWork() {
        return this;
    }

    public WorkLogBuilder forInterval(Interval interval) {
        maybeInterval = Optional.of(interval);
        return this;
    }

    public TimeCard timeCard() {
        final List<Punch> punches = this.punches.punches();
        Interval intervalCovered = maybeInterval.orElseGet(new Supplier<Interval>() {
            @Override
            public Interval get() {
                return WorkLogBuilder.this.punches.intervalCovered();
            }
        });
        return new TimeCard(intervalCovered, punches);
    }

    private DateTimeBuilder day() {
        return day.copy().withInputTimeZone(inputTimeZone);
    }
}
