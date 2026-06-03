package com.github.signed.timeless.specialdays;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.signed.timeless.specialdays.SpecialDays.shroveMonday;
import static com.github.signed.timeless.time.Month.February;
import static com.github.signed.timeless.time.Month.March;
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
                Arguments.of(date(2025, March, 3)),
                Arguments.of(date(2026, February, 16)),
                Arguments.of(date(2027, February, 8)),
                Arguments.of(date(2028, February, 28))
        );
    }

    private static LocalDate date(int year, Month monthOfYear, int dayOfMonth) {
        return LocalDate.of(year, monthOfYear, dayOfMonth);
    }

}
