package com.github.signed.timeless.workhours;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.signed.timeless.storage.DateTimeMother;

public class PersonalTimeOff_MultipleDaysTest {

    private final PersonalTimeOff personalTimeOff = new PersonalTimeOff();
    private final WorkHoursPerDayBuilder builder = Mockito.mock(WorkHoursPerDayBuilder.class);

    private final LocalDate start = DateTimeMother.AnyWorkday();
    private final LocalDate end = start.plusDays(5);

    @Before
    public void add_time_of_over_multiple_days() throws Exception {
        personalTimeOff.timeOff(start, end);
    }

    @Test
    public void day_before_start_day_you_have_to_work(){
        personalTimeOff.adjustHoursToWorkFor(start.minusDays(1), builder);

        verifyZeroInteractions(builder);
    }

    @Test
    public void start_day_is_work_free() throws Exception {
        personalTimeOff.adjustHoursToWorkFor(start, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    public void all_days_in_between_are_work_free() throws Exception {
        LocalDate anyDayBetweenStartAndEnd = start.plusDays(2);
        personalTimeOff.adjustHoursToWorkFor(anyDayBetweenStartAndEnd, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    public void until_day_is_included() throws Exception {
        personalTimeOff.adjustHoursToWorkFor(end, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    public void all_days_after_the_end_day_you_have_to_work_again() throws Exception {
        personalTimeOff.adjustHoursToWorkFor(end.plusDays(1), builder);

        verifyZeroInteractions(builder);
    }
}