package com.github.signed.timeless.holidays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.joda.time.LocalDate;
import org.junit.Test;

public class ExtraordinaryHolidaysTest {

    @Test
    public void _500_year_anniversary_of_reformation_day_is_a_national_holiday_in_Germany() throws Exception {

        assertThat(new ExtraordinaryHolidays().holidaysFor(2017).get(0).date, equalTo(new LocalDate(2017, 10, 31)));
    }
}