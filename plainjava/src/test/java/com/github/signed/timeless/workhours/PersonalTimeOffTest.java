package com.github.signed.timeless.workhours;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.github.signed.timeless.storage.DateTimeMother;

class PersonalTimeOffTest {

    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);
    private final PersonalTimeOff personalTimeOff = new PersonalTimeOff();
    private final LocalDate workday = DateTimeMother.AnyWorkday();

    @Test
    void contributeToWorkHoursPerDayIfPersonalTimeOfWasTakenForThisDay() {
        personalTimeOff.dayOffAt(workday);
        personalTimeOff.adjustHoursToWorkFor(workday, builder);
        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    void contributeToWorkHoursPerDayIfHalfADayOfPersonalTimeOfWasTakenForThisDay() {
        personalTimeOff.halfADayOffAt(workday);
        personalTimeOff.adjustHoursToWorkFor(workday, builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    void doNotContributeToWorkHoursPerDayIfNoTimeOfWasTakenAtThatDay() {
        personalTimeOff.adjustHoursToWorkFor(workday, builder);
        verifyZeroInteractions(builder);
    }
}