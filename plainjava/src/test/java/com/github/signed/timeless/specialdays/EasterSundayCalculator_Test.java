package com.github.signed.timeless.specialdays;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static com.github.signed.timeless.time.Month.April;
import static com.github.signed.timeless.time.Month.March;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class EasterSundayCalculator_Test {

    static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{2015, April, 5}, {2016, March, 27}, {2017, April, 16}, {2018, April, 1}, {2019, April, 21}, {2020, April, 12}, {2021, April, 4}, {2022, April, 17}});
    }

    @ParameterizedTest
    @MethodSource("data")
    void calculateEasterSunday(int year, Month month, int dayOfMonth) {
        LocalDate easterSunday = LocalDate.of(year, month, dayOfMonth);
        assertThat(new EasterSundayCalculator().easterSundayFor(year), is(easterSunday));
    }
}
