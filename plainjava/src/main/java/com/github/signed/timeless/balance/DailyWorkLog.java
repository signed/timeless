package com.github.signed.timeless.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import com.github.signed.timeless.Punch;

public class DailyWorkLog {
    private static class ComparatorByDateTime implements Comparator<Punch> {
        @Override
        public int compare(Punch o1, Punch o2) {
            return o1.dateTime().compareTo(o2.dateTime());
        }
    }


    public final LocalDate day;
    public final List<Punch> value;

    public DailyWorkLog(LocalDate day, List<Punch> value) {
        this.day = day;
        this.value = new ArrayList<Punch>(value);
        Collections.sort(value, new ComparatorByDateTime());
    }

    public Duration timeWorked() {
        Duration beforeLunch = new Interval(value.get(0).dateTime(), value.get(1).dateTime()).toDuration();
        Duration afterLunch = Duration.ZERO;
        Duration inTheEvening = Duration.ZERO;
        if (value.size() >= 4) {
            afterLunch = new Interval(value.get(2).dateTime(), value.get(3).dateTime()).toDuration();
        }
        if(value.size() >= 6){
            inTheEvening = new Interval(value.get(4).dateTime(), value.get(5).dateTime()).toDuration();
        }
        return beforeLunch.plus(afterLunch).plus(inTheEvening);
    }
}
