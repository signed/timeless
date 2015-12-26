package com.github.signed.timeless;

import static com.github.signed.timeless.Punch.compareByTimeStamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public LocalDate from() {
        return intervalCovered.getStart().toLocalDate();
    }

    public LocalDate until() {
        return intervalCovered.getEnd().toLocalDate().minusDays(1);
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
}
