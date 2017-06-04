package com.github.signed.timeless.balance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Collections;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.ConsecutiveTime;
import com.github.signed.timeless.storage.DateTimeMother;

public class DailyWorkLog_Test {

    @Test
    public void if_there_are_no_hours_punched_there_was_no_work_done_for_this_day() throws Exception {
        LocalDate day = DateTimeMother.AnyWorkday();

        assertThat(new DailyWorkLog(day, Collections.<ConsecutiveTime>emptyList()).timeWorked(), is(Duration.ZERO));
    }

}