package com.github.signed.timeless.storage;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.github.signed.timeless.TimeCard;

public class WorkLogBuilder {
    private final PunchesBuilder punches = new PunchesBuilder();
    private DateTimeZone inputTimeZone = DateTimeZone.UTC;
    private DateTimeBuilder day;

    public WorkLogBuilder on(LocalDate localDate) {
        return on(new DateTimeBuilder().on(localDate));
    }

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

    public WorkLogBuilder didNotWork() {
        return this;
    }

    public TimeCard timeCard() {
        return new TimeCard(punches.punches());
    }

    private DateTimeBuilder day() {
        return day.copy().withInputTimeZone(inputTimeZone);
    }
}
