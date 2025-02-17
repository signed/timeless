package com.github.signed.timeless.specialdays;

import org.joda.time.DateTimeConstants;

public class SpecialDays {
    private static final EasterSundayCalculator easterSunday = new EasterSundayCalculator();

    public static SpecialDay christmas() {
        return (day) -> day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 24;
    }

    public static SpecialDay newYearsEve() {
        return (day) -> day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 31;
    }

    public static SpecialDay shroveMonday() {
        return day -> easterSunday.easterSundayFor(day.getYear()).minusDays(48).equals(day);
    }
}
