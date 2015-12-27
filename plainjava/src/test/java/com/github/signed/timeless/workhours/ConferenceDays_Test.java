package com.github.signed.timeless.workhours;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.signed.timeless.storage.DateTimeMother;

public class ConferenceDays_Test {

    private final ConferenceDays conferenceDays = new ConferenceDays();
    private final LocalDate day = DateTimeMother.AnyWorkday();
    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);

    @Test
    public void when_you_are_not_on_a_conference_you_go_to_work_and_do_your_expected_hours() throws Exception {
        conferenceDays.adjustHoursToWorkFor(day, builder);

        verifyZeroInteractions(builder);
    }

    @Test
    public void normally_you_are_at_a_conference_for_more_than_one_day() throws Exception {
        LocalDate until = day.plusDays(2);

        conferenceDays.wasAtConference(day, until);

        conferenceDays.adjustHoursToWorkFor(day, builder);
        verify(builder).reduceByCompleteWorkDay();
        Mockito.reset(builder);

        conferenceDays.adjustHoursToWorkFor(day.plusDays(1), builder);
        verify(builder).reduceByCompleteWorkDay();
        Mockito.reset(builder);

        conferenceDays.adjustHoursToWorkFor(day.plusDays(2), builder);
        verify(builder).reduceByCompleteWorkDay();
        Mockito.reset(builder);

        conferenceDays.adjustHoursToWorkFor(day.plusDays(3), builder);
        verifyZeroInteractions(builder);
    }

    @Test
    public void when_at_a_conference_have_to_log_work() throws Exception {
        conferenceDays.wasAtConference(day);
        conferenceDays.adjustHoursToWorkFor(day, builder);

        verify(builder).reduceByCompleteWorkDay();
    }
}