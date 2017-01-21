package com.github.signed.timeless.holidays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTimeConstants.APRIL;
import static org.joda.time.DateTimeConstants.DECEMBER;
import static org.joda.time.DateTimeConstants.JANUARY;
import static org.joda.time.DateTimeConstants.JUNE;
import static org.joda.time.DateTimeConstants.MAY;
import static org.joda.time.DateTimeConstants.NOVEMBER;
import static org.joda.time.DateTimeConstants.OCTOBER;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.github.signed.timeless.holidays.HolidayCalculator;

public class HolidayCalculator_2015Test {

    @Test
    public void new_year_is_always_january_first() throws Exception {
        assertThat(dateOfHoliday(0), is(_2015(JANUARY, 1)));
    }

    @Test
    public void epiphany_is_always_january_sixth() throws Exception {
        assertThat(dateOfHoliday(1), is(_2015(JANUARY, 6)));
    }

    @Test
    public void good_friday_is_two_days_before_easter_sunday() throws Exception {
        assertThat(dateOfHoliday(2), is(_2015(APRIL, 3)));
    }

    @Test
    public void easter_monday_is_one_day_after_easter_sunday() throws Exception {
        assertThat(dateOfHoliday(3), is(_2015(APRIL, 6)));
    }

    @Test
    public void labour_day_is_always_may_first() throws Exception {
        assertThat(dateOfHoliday(4), is(_2015(MAY, 1)));
    }

    @Test
    public void ascension_day_is_39_days_after_easter_sunday() throws Exception {
        assertThat(dateOfHoliday(5), is(_2015(MAY, 14)));
    }

    @Test
    public void whit_monday_is_50_days_after_easter_sunday() throws Exception {
        assertThat(dateOfHoliday(6), is(_2015(MAY, 25)));
    }

    @Test
    public void corpus_christi_is_60_days_after_easter_sunday() throws Exception {
        assertThat(dateOfHoliday(7), is(_2015(JUNE, 4)));
    }

    @Test
    public void anniversary_of_German_unification_is_always_october_third() throws Exception {
        assertThat(dateOfHoliday(8), is(_2015(OCTOBER, 3)));
    }

    @Test
    public void all_saints_is_always_november_first() throws Exception {
        assertThat(dateOfHoliday(9), is(_2015(NOVEMBER, 1)));
    }

    @Test
    public void christmas_day_is_always_december_25() throws Exception {
        assertThat(dateOfHoliday(10), is(_2015(DECEMBER, 25)));
    }

    @Test
    public void boxing_day_is_always_december_26() throws Exception {
        assertThat(dateOfHoliday(11), is(_2015(DECEMBER, 26)));
    }

    private LocalDate _2015(int monthOfYear, int dayOfMonth) {
        return new LocalDate(2015, monthOfYear, dayOfMonth);
    }

    private LocalDate dateOfHoliday(int index) {
        return new HolidayCalculator().holidaysFor(2015).get(index).date;
    }
}