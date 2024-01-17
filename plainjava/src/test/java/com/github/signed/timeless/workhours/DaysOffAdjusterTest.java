package com.github.signed.timeless.workhours;

import static com.github.signed.timeless.MockitoWrapper.verifyZeroInteractions;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.github.signed.timeless.storage.DateTimeMother;

class DaysOffAdjusterTest {

    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);
    private final DaysOffAdjuster daysOffAdjuster = new DaysOffAdjuster();
    private final LocalDate workday = DateTimeMother.AnyWorkday();

    @Test
    void contributeToWorkHoursPerDayIfPersonalTimeOfWasTakenForThisDay() {
        daysOffAdjuster.dayOffAt(workday);
        daysOffAdjuster.adjustHoursToWorkFor(workday, builder);
        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    void contributeToWorkHoursPerDayIfHalfADayOfPersonalTimeOfWasTakenForThisDay() {
        daysOffAdjuster.halfADayOffAt(workday);
        daysOffAdjuster.adjustHoursToWorkFor(workday, builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    void doNotContributeToWorkHoursPerDayIfNoTimeOfWasTakenAtThatDay() {
        daysOffAdjuster.adjustHoursToWorkFor(workday, builder);
        verifyZeroInteractions(builder);
    }
}
