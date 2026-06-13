package com.github.signed.timeless.time;

import com.github.signed.timeless.time.joda.JodaTimeSource;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

class LocalDateTest {

    @Test
    void asISOString() {
        var localDate = JodaTimeSource.instance.localDateOf(2026, Month.January, 7);
        assertThat(localDate.asISOString(), CoreMatchers.equalTo("2026-01-07"));
    }
}