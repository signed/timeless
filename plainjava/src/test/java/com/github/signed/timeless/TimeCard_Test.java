package com.github.signed.timeless;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.storage.WorkLogBuilder;

public class TimeCard_Test {

    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder();
    private final LocalDate firstWorkDay = DateTimeMother.AnyWorkday();
    private final LocalDate lastWorkDay = firstWorkDay.plusDays(3);

    @Before
    public void logWorkOverMultipleDaysInWrongOrder() throws Exception {
        workLogBuilder.on(lastWorkDay).workedFrom(FromUntilMother.anyAmountOfTime());
        workLogBuilder.on(firstWorkDay).workedFrom(FromUntilMother.anyAmountOfTime());
    }

    @Test
    public void deriveFromDayFromFirstPunch() throws Exception {
        assertThat(workLogBuilder.timeCard().from(), is(firstWorkDay));
    }

    @Test
    public void deriveUntilDayFormLastPunch() throws Exception {
        assertThat(workLogBuilder.timeCard().until(), is(lastWorkDay));
    }
}