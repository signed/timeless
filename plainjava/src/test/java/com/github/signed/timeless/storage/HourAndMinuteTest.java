package com.github.signed.timeless.storage;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class HourAndMinuteTest {

    @Test
    void checkForOur() {
        HourAndMinute in = HourAndMinute.parse("23:59");
        HourAndMinute out = HourAndMinute.parse("00:01");

        assertThat("should be earlier", out.earlierInDayThan(in));
    }

    @Test
    void checkForMinute() {
        HourAndMinute in = HourAndMinute.parse("00:01");
        HourAndMinute out = HourAndMinute.parse("00:00");

        assertThat("should be earlier", out.earlierInDayThan(in));
    }

    @Test
    void checkForHourAndMinute() {
        HourAndMinute in = HourAndMinute.parse("00:00");
        HourAndMinute out = HourAndMinute.parse("00:59");

        assertThat("should no be earlier", !out.earlierInDayThan(in));
    }
}