package com.github.signed.timeless.storage;

import java.util.Collection;

import org.joda.time.DateTimeZone;

import com.github.signed.timeless.Punch;

public class WorkLogBuilder {
    private final PunchesBuilder punches = new PunchesBuilder();
    private DateTimeZone inputTimeZone = DateTimeZone.UTC;
    private DateTimeBuilder day;

    public WorkLogBuilder on(DateTimeBuilder day) {
        this.day = day;
        return this;
    }

    public WorkLogBuilder inLocalTime() {
        inputTimeZone = DateTimeZone.getDefault();
        return this;
    }

    public WorkLogBuilder workedInSpareTime(String... multipleFromTills) {
        return workedFrom(multipleFromTills);
    }

    public WorkLogBuilder workedFrom(String... multipleFromTills) {
        for (String fromTill : multipleFromTills) {
            String[] split = fromTill.split("-");
            punches.punchInAt(day().at(split[0]));
            punches.punchOutAt(day().at(split[1]));
        }
        return this;
    }

    public Collection<Punch> punches() {
        return punches.punches();
    }

    private DateTimeBuilder day() {
        return day.copy().withInputTimeZone(inputTimeZone);
    }
}
