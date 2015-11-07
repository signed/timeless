package com.github.signed.timeless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TimeCard implements Iterable<Punch>{

    private final Collection<Punch> punches;

    public TimeCard(Collection<Punch> punches) {
        this.punches = new ArrayList<Punch>(punches);
    }

    @Override
    public Iterator<Punch> iterator() {
        return punches.iterator();
    }
}
