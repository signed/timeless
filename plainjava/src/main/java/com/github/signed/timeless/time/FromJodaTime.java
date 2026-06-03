package com.github.signed.timeless.time;

import org.joda.time.DateTimeConstants;

public class FromJodaTime {
    public static Month toMonth(org.joda.time.LocalDate day) {
        var monthInt = day.monthOfYear().get();
        return toMonth(monthInt);
    }

    public static Month toMonth(int monthInt) {
        return switch (monthInt) {
            case DateTimeConstants.JANUARY -> Month.January;
            case DateTimeConstants.FEBRUARY -> Month.February;
            case DateTimeConstants.MARCH -> Month.March;
            case DateTimeConstants.APRIL -> Month.April;
            case DateTimeConstants.MAY -> Month.May;
            case DateTimeConstants.JUNE -> Month.June;
            case DateTimeConstants.JULY -> Month.July;
            case DateTimeConstants.AUGUST -> Month.August;
            case DateTimeConstants.SEPTEMBER -> Month.September;
            case DateTimeConstants.OCTOBER -> Month.October;
            case DateTimeConstants.NOVEMBER -> Month.November;
            case DateTimeConstants.DECEMBER -> Month.December;
            default -> throw new IllegalArgumentException("Unknown month: " + monthInt);
        };
    }

    public static DayOfWeek toDayOfWeek(org.joda.time.LocalDate day) {
        return switch (day.getDayOfWeek()) {
            case DateTimeConstants.MONDAY -> DayOfWeek.Monday;
            case DateTimeConstants.TUESDAY -> DayOfWeek.Tuesday;
            case DateTimeConstants.WEDNESDAY -> DayOfWeek.Wednesday;
            case DateTimeConstants.THURSDAY -> DayOfWeek.Thursday;
            case DateTimeConstants.FRIDAY -> DayOfWeek.Friday;
            case DateTimeConstants.SATURDAY -> DayOfWeek.Saturday;
            case DateTimeConstants.SUNDAY -> DayOfWeek.Sunday;
            default -> throw new IllegalArgumentException("Unknown day of week: " + day.getDayOfWeek());
        };
    }
}
