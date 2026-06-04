package com.github.signed.timeless;

import com.github.signed.timeless.time.DateTimeZone;

public class Constants {
    public static DateTimeZone backendTimeZone() {
        return DateTimeZone.Utc;
    }

    public static DateTimeZone inputTimeZone() {
        return DateTimeZone.EuropeBerlin;
    }
}
