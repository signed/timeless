package com.github.signed.timeless.workhours;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.storage.DateTimeMother;

public class PersonalTimeOffTest {

    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);
    private final PersonalTimeOff personalTimeOff = new PersonalTimeOff();
    private final LocalDate workday = DateTimeMother.AnyWorkday();

    @Test
    public void contributeToWorkHoursPerDayIfPersonalTimeOfWasTakenForThisDay() throws Exception {
        personalTimeOff.dayOfAt(workday);
        personalTimeOff.adjustHoursToWorkFor(workday, builder);
        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    public void contributeToWorkHoursPerDayIfHalfADayOfPersonalTimeOfWasTakenForThisDay() throws Exception {
        personalTimeOff.halfADayOfAt(workday);
        personalTimeOff.adjustHoursToWorkFor(workday, builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    public void doNotContributeToWorkHoursPerDayIfNoTimeOfWasTakenAtThatDay() throws Exception {
        personalTimeOff.adjustHoursToWorkFor(workday, builder);
        verifyZeroInteractions(builder);
    }
}