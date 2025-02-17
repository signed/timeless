package com.github.signed.timeless.specialdays;

import org.joda.time.DateTimeConstants;

public class SpecialDays {
    public static SpecialDay christmas() {
        return (day) -> day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 24;
    }

    public static SpecialDay newYearsEve() {
        return (day) -> day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 31;
    }
}
