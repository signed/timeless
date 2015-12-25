package com.github.signed.timeless.workhours;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.github.signed.timeless.storage.DateTimeMother;

public class Holidays_Test {

    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);

    @Test
    public void pass_holiday_to_builder_if_day_is_an_actual_holiday() throws Exception {
        Holiday holiday = HolidayMother.anyHoliday();
        new Holidays().adjustHoursToWorkFor(holiday.date, builder);

        ArgumentCaptor<Holiday> captor = ArgumentCaptor.forClass(Holiday.class);
        verify(builder).holiday(captor.capture());

        assertThat(captor.getValue().date, is(holiday.date));
    }

    @Test
    public void do_not_interact_with_builder_if_day_is_not_a_holiday() throws Exception {
        LocalDate workday = DateTimeMother.AnyWorkday();
        new Holidays().adjustHoursToWorkFor(workday, builder);

        Mockito.verifyZeroInteractions(builder);
    }
}