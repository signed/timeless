package com.github.signed.timeless.storage;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class HourAndMinuteTest {

    @Test
    public void checkForOur() throws Exception {
        HourAndMinute in = HourAndMinute.parse("23:59");
        HourAndMinute out = HourAndMinute.parse("00:01");

        assertThat("should be earlier", out.earlierInDayThan(in));
    }

    @Test
    public void checkForMinute() throws Exception {
        HourAndMinute in = HourAndMinute.parse("00:01");
        HourAndMinute out = HourAndMinute.parse("00:00");

        assertThat("should be earlier", out.earlierInDayThan(in));
    }

    @Test
    public void checkForHourAndMinute() throws Exception {
        HourAndMinute in = HourAndMinute.parse("00:00");
        HourAndMinute out = HourAndMinute.parse("00:59");

        assertThat("should no be earlier", !out.earlierInDayThan(in));
    }
}