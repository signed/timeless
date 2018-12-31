package com.github.signed.timeless.holidays;

import static com.github.signed.timeless.storage.DateTimeMother.AnyWorkday;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

class Holidays_Test {

    private final WorkHoursPerDayBuilder builder = mock(WorkHoursPerDayBuilder.class);

    @Test
    void pass_holiday_to_builder_if_day_is_an_actual_holiday() {
        Holiday holiday = HolidayMother.anyHoliday();
        adjustHoursToWorkAt(holiday.date);

        ArgumentCaptor<Holiday> captor = ArgumentCaptor.forClass(Holiday.class);
        verify(builder).holiday(captor.capture());

        assertThat(captor.getValue().date, is(holiday.date));
    }

    @Test
    void pass_extraordinary_holiday_to_builder() {
        Holiday holiday = HolidayMother.extraordinaryHoliday();
        adjustHoursToWorkAt(holiday.date);

        ArgumentCaptor<Holiday> captor = ArgumentCaptor.forClass(Holiday.class);
        verify(builder).holiday(captor.capture());

        assertThat(captor.getValue().date, is(holiday.date));
    }

    @Test
    void do_not_interact_with_builder_if_day_is_not_a_holiday() {
        adjustHoursToWorkAt(AnyWorkday());

        verifyZeroInteractions(builder);
    }

    private void adjustHoursToWorkAt(LocalDate workday) {
        new Holidays().adjustHoursToWorkFor(workday, builder);
    }
}