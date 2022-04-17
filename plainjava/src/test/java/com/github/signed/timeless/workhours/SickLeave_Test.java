package com.github.signed.timeless.workhours;

import static com.github.signed.timeless.MockitoWrapper.verifyZeroInteractions;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.github.signed.timeless.storage.DateTimeMother;

class SickLeave_Test {

    private final SickLeave sickLeave = new SickLeave();
    private final LocalDate day = DateTimeMother.AnyWorkday();
    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);

    @Test
    void when_your_are_sick_you_do_not_have_to_work() {
        sickLeave.wasSickOn(day);
        sickLeave.adjustHoursToWorkFor(day, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    void when_you_are_healthy_you_go_to_work_and_do_your_expected_hours() {
        sickLeave.adjustHoursToWorkFor(day, builder);

        verifyZeroInteractions(builder);
    }
}