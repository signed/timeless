package com.github.signed.timeless.storage;

import static com.github.signed.timeless.Punch.compareByTimeStamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import com.github.signed.timeless.Punch;

public class PunchesBuilder {
    private final List<Punch> punches = new ArrayList<Punch>();

    public static LocalDate fromInternal(List<Punch> punches) {
        return punches.get(0).dateTime().toLocalDate();
    }

    public static LocalDate untilInternal(List<Punch> punches) {
        return punches.get(punches.size() - 1).dateTime().toLocalDate();
    }

    public void punchInAt(DateTimeBuilder dateTime) {
        punches.add(Punch.PunchIn(dateTime.buildUtc()));
    }

    public void punchOutAt(DateTimeBuilder dateTime) {
        punches.add(Punch.PunchOut(dateTime.buildUtc()));
    }

    public Interval intervalCovered() {
        List<Punch> sortedPunches = sortedPunches();
        DateTime start = fromInternal(sortedPunches).toDateTimeAtStartOfDay(DateTimeZone.UTC);
        DateTime end = untilInternal(sortedPunches).plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC);
        return new Interval(start, end);
    }

    public List<Punch> punches(){
        return new ArrayList<Punch>(punches);
    }

    private List<Punch> sortedPunches() {
        List<Punch> sortedPunches = punches();
        Collections.sort(sortedPunches, compareByTimeStamp());
        return sortedPunches;
    }
}
