package com.github.signed.timeless.holidays;

import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTimeConstants.APRIL;
import static org.joda.time.DateTimeConstants.MARCH;

import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.MatcherAssert;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.github.signed.timeless.workhours.EasterSundayCalculator;

@RunWith(Parameterized.class)
public class EasterSundayCalculator_Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2015, APRIL, 5},
                {2016, MARCH, 27},
                {2017, APRIL, 16},
                {2018, APRIL, 1},
                {2019, APRIL, 21},
                {2020, APRIL, 12},
                {2021, APRIL, 4},
                {2022, APRIL, 17}
        });
    }

    private final int year;

    private final LocalDate easterSunday;

    public EasterSundayCalculator_Test(int year, int month, int dayOfMonth) {
        this.year = year;
        this.easterSunday = new LocalDate(year, month, dayOfMonth);
    }

    @Test
    public void calculateEasterSunday() throws Exception {
        MatcherAssert.assertThat(new EasterSundayCalculator().easterSundayFor(year), is(easterSunday));
    }
}