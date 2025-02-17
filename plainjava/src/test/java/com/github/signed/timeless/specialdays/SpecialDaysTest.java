package com.github.signed.timeless.specialdays;

import org.joda.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.signed.timeless.specialdays.SpecialDays.shroveMonday;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SpecialDaysTest {

    @ParameterizedTest
    @MethodSource("shroveMondays")
    void shrove_Monday(LocalDate day) {
        assertThat(shroveMonday().test(day), is(true));
    }

    private static Stream<Arguments> shroveMondays() {
        return Stream.of(
                Arguments.of(date(2025, 3, 3)),
                Arguments.of(date(2026, 2, 16)),
                Arguments.of(date(2027, 2, 8)),
                Arguments.of(date(2028, 2, 28))
        );
    }

    private static LocalDate date(int year, int monthOfYear, int dayOfMonth) {
        return new LocalDate(year, monthOfYear, dayOfMonth);
    }

}
