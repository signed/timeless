package com.github.signed.timeless.contract;

import static com.github.signed.timeless.storage.DateTimeMother.AnyChristmasEve;
import static com.github.signed.timeless.storage.DateTimeMother.AnyNewYearsEve;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.signed.timeless.storage.DateTimeMother;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

public class EmployerCourtesy_Test {

    private final EmployerCourtesy employerCourtesy = new EmployerCourtesy();
    private final WorkHoursPerDayBuilder builder = Mockito.mock(WorkHoursPerDayBuilder.class);

    @Test
    public void christmasIsOnlyHalfADayOfWork() throws Exception {
        employerCourtesy.adjustHoursToWorkFor(AnyChristmasEve(), builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    public void newYearsEveIsOnlyHalfADayOfWork() throws Exception {
        employerCourtesy.adjustHoursToWorkFor(AnyNewYearsEve(), builder);
        verify(builder).reduceByHalfAWorkDay();
    }

    @Test
    public void forAnyOtherDayDoNotAlterTheHourToWork() throws Exception {
        employerCourtesy.adjustHoursToWorkFor(DateTimeMother.AnyDayNotChristmasEveOrNewYearsEve(), builder);
        verifyZeroInteractions(builder);
    }
}