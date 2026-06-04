package com.github.signed.timeless.time;

import org.joda.time.DateTimeConstants;

public class ToJodaTime {
    static int toDateTimeConstant(Month month) {
        return switch (month) {
            case January -> DateTimeConstants.JANUARY;
            case February -> DateTimeConstants.FEBRUARY;
            case March -> DateTimeConstants.MARCH;
            case April -> DateTimeConstants.APRIL;
            case May -> DateTimeConstants.MAY;
            case June -> DateTimeConstants.JUNE;
            case July -> DateTimeConstants.JULY;
            case August -> DateTimeConstants.AUGUST;
            case September -> DateTimeConstants.SEPTEMBER;
            case October -> DateTimeConstants.OCTOBER;
            case November -> DateTimeConstants.NOVEMBER;
            case December -> DateTimeConstants.DECEMBER;
        };
    }

    static org.joda.time.DateTimeZone toJodaTime(DateTimeZone dateTimeZone) {
        return switch (dateTimeZone) {
            case Utc -> org.joda.time.DateTimeZone.UTC;
            case EuropeBerlin -> org.joda.time.DateTimeZone.forID("Europe/Berlin");
        };
    }
}
