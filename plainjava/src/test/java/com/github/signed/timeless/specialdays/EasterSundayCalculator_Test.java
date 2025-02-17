package com.github.signed.timeless.specialdays;

import org.joda.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTimeConstants.APRIL;
import static org.joda.time.DateTimeConstants.MARCH;


class EasterSundayCalculator_Test {

    static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{2015, APRIL, 5}, {2016, MARCH, 27}, {2017, APRIL, 16}, {2018, APRIL, 1}, {2019, APRIL, 21}, {2020, APRIL, 12}, {2021, APRIL, 4}, {2022, APRIL, 17}});
    }

    @ParameterizedTest
    @MethodSource("data")
    void calculateEasterSunday(int year, int month, int dayOfMonth) {
        LocalDate easterSunday = new LocalDate(year, month, dayOfMonth);
        assertThat(new EasterSundayCalculator().easterSundayFor(year), is(easterSunday));
    }
}
