package com.github.signed.timeless.workhours;

import static com.github.signed.timeless.MockitoWrapper.verifyZeroInteractions;
import static org.mockito.Mockito.verify;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.github.signed.timeless.storage.DateTimeMother;

class PersonalTimeOff_MultipleDaysTest {

    private final PersonalTimeOff personalTimeOff = new PersonalTimeOff();
    private final WorkHoursPerDayBuilder builder = Mockito.mock(WorkHoursPerDayBuilder.class);

    private final LocalDate start = DateTimeMother.AnyWorkday();
    private final LocalDate end = start.plusDays(5);

    @BeforeEach
    void add_time_of_over_multiple_days() {
        personalTimeOff.timeOff(start, end);
    }

    @Test
    void day_before_start_day_you_have_to_work(){
        personalTimeOff.adjustHoursToWorkFor(start.minusDays(1), builder);

        verifyZeroInteractions(builder);
    }

    @Test
    void start_day_is_work_free() {
        personalTimeOff.adjustHoursToWorkFor(start, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    void all_days_in_between_are_work_free() {
        LocalDate anyDayBetweenStartAndEnd = start.plusDays(2);
        personalTimeOff.adjustHoursToWorkFor(anyDayBetweenStartAndEnd, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    void until_day_is_included() {
        personalTimeOff.adjustHoursToWorkFor(end, builder);

        verify(builder).reduceByCompleteWorkDay();
    }

    @Test
    void all_days_after_the_end_day_you_have_to_work_again() {
        personalTimeOff.adjustHoursToWorkFor(end.plusDays(1), builder);

        verifyZeroInteractions(builder);
    }
}