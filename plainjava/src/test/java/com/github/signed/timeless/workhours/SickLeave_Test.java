package com.github.signed.timeless.workhours;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.storage.DateTimeMother;

public class SickLeave_Test {

    private final SickLeave sickLeave = new SickLeave();
    private final LocalDate day = DateTimeMother.AnyWorkday();
    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);

    @Test
    public void when_your_are_sick_you_do_not_have_to_work() throws Exception {
        sickLeave.wasSickOn(day);
        sickLeave.adjustHoursToWorkFor(day, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    public void when_you_are_healty_you_go_to_work_and_do_your_expected_hours() throws Exception {
        sickLeave.adjustHoursToWorkFor(day, builder);

        verifyZeroInteractions(builder);
    }
}