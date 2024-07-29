package com.github.signed.timeless;

import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.storage.WorkLogBuilder;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TimeCard_Test {

    private final WorkLogBuilder workLogBuilder = new WorkLogBuilder();
    private final LocalDate firstWorkDay = DateTimeMother.AnyWorkday();
    private final LocalDate lastWorkDay = firstWorkDay.plusDays(3);

    @BeforeEach
    void logWorkOverMultipleDaysInWrongOrder() {
        workLogBuilder.on(firstWorkDay).workedFrom(FromUntilMother.anyAmountOfTime());
        workLogBuilder.on(lastWorkDay).workedFrom(FromUntilMother.anyAmountOfTime());
    }

    @Test
    void deriveFromDayFromFirstPunch() {
        assertThat(workLogBuilder.timeCard().from(), is(firstWorkDay));
    }

    @Test
    void coversDayOfLastPunch() {
        TimeCard timeCard = workLogBuilder.timeCard();

        assertThat("the time card should cover the last workday", timeCard.covers(lastWorkDay));
    }
}
