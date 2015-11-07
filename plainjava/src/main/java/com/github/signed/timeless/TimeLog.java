package com.github.signed.timeless;

import org.joda.time.Interval;

public interface TimeLog {

    TimeCardInterface time_card_for(Interval interval);

    Punch last_punch();
}
