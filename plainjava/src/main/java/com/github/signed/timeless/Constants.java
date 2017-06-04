package com.github.signed.timeless;

import org.joda.time.DateTimeZone;

public class Constants {
    public static DateTimeZone backendTimeZone() {
        return DateTimeZone.UTC;
    }

    public static DateTimeZone frontendTimeZone() {
        return DateTimeZone.forID("Europe/Berlin");
    }
}
