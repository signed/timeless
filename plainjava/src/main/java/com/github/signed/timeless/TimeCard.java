package com.github.signed.timeless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.joda.time.LocalDate;

public class TimeCard implements Iterable<Punch>{

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

    @Override
    public Iterator<Punch> iterator() {
        return punches.iterator();
    }
}
