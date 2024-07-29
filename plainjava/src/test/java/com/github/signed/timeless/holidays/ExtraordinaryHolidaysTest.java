package com.github.signed.timeless.holidays;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ExtraordinaryHolidaysTest {

    @Test
    void _500_year_anniversary_of_reformation_day_is_a_national_holiday_in_Germany() {

        assertThat(new ExtraordinaryHolidays().holidaysFor(2017).get(0).date, equalTo(new LocalDate(2017, 10, 31)));
    }
}
