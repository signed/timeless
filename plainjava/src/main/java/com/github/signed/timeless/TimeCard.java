package com.github.signed.timeless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

public class TimeCard{

    private final List<Punch> punches;

    public TimeCard(Collection<Punch> punches) {
        this.punches = new ArrayList<Punch>(punches);
        Collections.sort(this.punches, new Comparator<Punch>() {
            @Override
            public int compare(Punch o1, Punch o2) {
                return o1.dateTime().compareTo(o2.dateTime());
            }
        });
    }

    public LocalDate from() {
        return punches.get(0).dateTime().toLocalDate();
    }

    public LocalDate until() {
        return punches.get(punches.size()-1).dateTime().toLocalDate();
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
