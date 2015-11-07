package com.github.signed.timeless;

import java.util.Collection;

import org.joda.time.Interval;

public class BasicTimeLog implements TimeLog, CheckClock {

    public BasicTimeLog(Clock clock, Collection<Punch> punches) {

    }

    @Override
    public void punch_in() {

    }

    @Override
    public void punch_out() {

    }

    @Override
    public TimeCardInterface time_card_for(Interval interval) {
        return null;
    }

    @Override
    public Punch last_punch() {
        return null;
    }
}
