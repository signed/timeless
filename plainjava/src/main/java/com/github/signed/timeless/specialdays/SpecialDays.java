package com.github.signed.timeless.specialdays;

import com.github.signed.timeless.time.Month;
import org.joda.time.DateTimeConstants;

public class SpecialDays {
    private static final EasterSundayCalculator easterSunday = new EasterSundayCalculator();

    public static SpecialDay christmas() {
        return (day) -> day.is(Month.December) && day.isDayOfMonth(24);
    }

    public static SpecialDay newYearsEve() {
        return (day) -> day.is(Month.December) && day.isDayOfMonth(31);
    }

    public static SpecialDay shroveMonday() {
        return day -> easterSunday.easterSundayFor(day.getYear()).minusDays(48).equals(day);
    }
}
