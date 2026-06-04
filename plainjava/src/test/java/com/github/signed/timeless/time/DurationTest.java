package com.github.signed.timeless.time;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DurationTest {

    @Test
    void asStringFormatting() {
        assertThat(Duration.ZERO().asString(), equalTo("+   00"));
        assertThat(Duration.standardMinutes(7).asString(), equalTo("+   07"));
        assertThat(Duration.standardMinutes(70).asString(), equalTo("+01:10"));
        assertThat(Duration.standardDays(2).asString(), equalTo("+48:00"));
        assertThat(Duration.standardHours(-1).asString(), equalTo("-01:00"));
    }
}