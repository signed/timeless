package com.github.signed.timeless;

import static com.github.signed.timeless.Punch.compareByTimeStamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

public class TimeCard {

    private final Interval intervalCovered;
    private final List<Punch> punches;

    public TimeCard(Interval intervalCovered, List<Punch> punches) {
        this.punches = new ArrayList<Punch>(punches);
        Collections.sort(this.punches, compareByTimeStamp());
        this.intervalCovered = intervalCovered;
    }

    public static List<ConsecutiveTime> consecutiveTimesFor(LocalDate day, List<Punch> value) {
        Collections.sort(value, new ComparatorByDateTime());
        List<ConsecutiveTime> consecutiveTimes = new ArrayList<ConsecutiveTime>();
        if (!value.isEmpty()) {
            consecutiveTimes.add(new ConsecutiveTime(value.get(0), value.get(1)));
        }
        if (value.size() >= 4) {
            consecutiveTimes.add(new ConsecutiveTime(value.get(2), value.get(3)));
        }
        if (value.size() >= 6) {
            consecutiveTimes.add(new ConsecutiveTime(value.get(4), value.get(5)));
        }
        return consecutiveTimes;
    }

    public LocalDate from() {
        return intervalCovered.getStart().toLocalDate();
    }

    public boolean covers(LocalDate day){
        return intervalCovered.contains(day.toDateTimeAtStartOfDay(DateTimeZone.UTC));
    }

    public Map<LocalDate, List<Punch>> punchesPerDay() {
        Map<LocalDate, List<Punch>> punchesPerDay = new HashMap<LocalDate, List<Punch>>();

        for (Punch punch : punches) {
            LocalDate day = punch.dateTime().toLocalDate();

            List<Punch> punchesAtCurrentDay = punchesPerDay.get(day);
            if (null == punchesAtCurrentDay) {
                punchesAtCurrentDay = new ArrayList<Punch>();
                punchesPerDay.put(day, punchesAtCurrentDay);
            }
            punchesAtCurrentDay.add(punch);
        }
        return punchesPerDay;
    }

    public static class ComparatorByDateTime implements Comparator<Punch> {

        @Override
        public int compare(Punch o1, Punch o2) {
            return o1.dateTime().compareTo(o2.dateTime());
        }
    }
}
