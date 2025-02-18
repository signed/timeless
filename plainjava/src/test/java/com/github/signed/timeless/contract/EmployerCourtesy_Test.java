package com.github.signed.timeless.contract;

import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.github.signed.timeless.MockitoWrapper.verifyZeroInteractions;
import static com.github.signed.timeless.storage.DateTimeMother.AnyChristmasEve;
import static com.github.signed.timeless.storage.DateTimeMother.AnyNewYearsEve;
import static org.mockito.Mockito.verify;

class EmployerCourtesy_Test {

    private final EmployerCourtesy employerCourtesy = EmployerCourtesy.halfDayOffOn();
    private final WorkHoursPerDayBuilder builder = Mockito.mock(WorkHoursPerDayBuilder.class);

    @Test
    void christmasIsOnlyHalfADayOfWork() {
        employerCourtesy.adjustHoursToWorkFor(AnyChristmasEve(), builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    void newYearsEveIsOnlyHalfADayOfWork() {
        employerCourtesy.adjustHoursToWorkFor(AnyNewYearsEve(), builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    void forAnyOtherDayDoNotAlterTheHourToWork() {
        employerCourtesy.adjustHoursToWorkFor(DateTimeMother.AnyDayNotChristmasEveOrNewYearsEve(), builder);
        verifyZeroInteractions(builder);
    }
}
