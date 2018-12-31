package com.github.signed.timeless.balance;

import static com.github.signed.timeless.Constants.frontendTimeZone;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Collections;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.github.signed.timeless.ConsecutiveTime;
import com.github.signed.timeless.storage.DateTimeMother;

class DailyWorkLog_Test {

    @Test
    void if_there_are_no_hours_punched_there_was_no_work_done_for_this_day() {
        LocalDate day = DateTimeMother.AnyWorkday();

        assertThat(new DailyWorkLog(day, frontendTimeZone(), Collections.<ConsecutiveTime>emptyList()).timeWorked(), is(Duration.ZERO));
    }

}