package com.github.signed.timeless.holidays;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTimeConstants.*;

class HolidayCalculator_2015Test {

    @Test
    void new_year_is_always_january_first() {
        assertThat(dateOfHoliday(0), is(_2015(JANUARY, 1)));
    }

    @Test
    void epiphany_is_always_january_sixth() {
        assertThat(dateOfHoliday(1), is(_2015(JANUARY, 6)));
    }

    @Test
    void good_friday_is_two_days_before_easter_sunday() {
        assertThat(dateOfHoliday(2), is(_2015(APRIL, 3)));
    }

    @Test
    void easter_monday_is_one_day_after_easter_sunday() {
        assertThat(dateOfHoliday(3), is(_2015(APRIL, 6)));
    }

    @Test
    void labour_day_is_always_may_first() {
        assertThat(dateOfHoliday(4), is(_2015(MAY, 1)));
    }

    @Test
    void ascension_day_is_39_days_after_easter_sunday() {
        assertThat(dateOfHoliday(5), is(_2015(MAY, 14)));
    }

    @Test
    void whit_monday_is_50_days_after_easter_sunday() {
        assertThat(dateOfHoliday(6), is(_2015(MAY, 25)));
    }

    @Test
    void corpus_christi_is_60_days_after_easter_sunday() {
        assertThat(dateOfHoliday(7), is(_2015(JUNE, 4)));
    }

    @Test
    void anniversary_of_German_unification_is_always_october_third() {
        assertThat(dateOfHoliday(8), is(_2015(OCTOBER, 3)));
    }

    @Test
    void all_saints_is_always_november_first() {
        assertThat(dateOfHoliday(9), is(_2015(NOVEMBER, 1)));
    }

    @Test
    void christmas_day_is_always_december_25() {
        assertThat(dateOfHoliday(10), is(_2015(DECEMBER, 25)));
    }

    @Test
    void boxing_day_is_always_december_26() {
        assertThat(dateOfHoliday(11), is(_2015(DECEMBER, 26)));
    }

    private LocalDate _2015(int monthOfYear, int dayOfMonth) {
        return new LocalDate(2015, monthOfYear, dayOfMonth);
    }

    private LocalDate dateOfHoliday(int index) {
        return new HolidayCalculator().holidaysFor(2015).get(index).date;
    }
}
