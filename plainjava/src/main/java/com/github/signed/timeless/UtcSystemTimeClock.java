package com.github.signed.timeless;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class UtcSystemTimeClock implements Clock{
    @Override
    public DateTime now() {
        return new DateTime(DateTimeZone.UTC);
    }
}
