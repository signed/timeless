package com.github.signed.timeless;

import java.util.Collection;
import java.util.Collections;

public class TrackYourTime {

    public static void main(String[] args) {
        Clock clock = new UtcSystemTimeClock();
        Collection<Punch> punches = Collections.emptyList();
        BasicTimeLog basicTimeLog = new BasicTimeLog(clock, punches);

    }
}
