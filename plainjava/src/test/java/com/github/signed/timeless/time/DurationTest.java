package com.github.signed.timeless.time;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DurationTest {

    @Test
    void asBalanceStringFormatting() {
        assertThat(Duration.ZERO().asBalanceString(), equalTo("+   00"));
        assertThat(Duration.standardMinutes(7).asBalanceString(), equalTo("+   07"));
        assertThat(Duration.standardMinutes(70).asBalanceString(), equalTo("+01:10"));
        assertThat(Duration.standardDays(2).asBalanceString(), equalTo("+48:00"));
        assertThat(Duration.standardHours(-1).asBalanceString(), equalTo("-01:00"));
    }

    @Test
    void toMinutes() {
        assertThat(Duration.ZERO().toMinutes(), equalTo(0L));
        assertThat(Duration.standardMinutes(1).toMinutes(), equalTo(1L));
        assertThat(Duration.standardMinutes(-1).toMinutes(), equalTo(-1L));
        assertThat(Duration.standardHours(1).toMinutes(), equalTo(60L));
        assertThat(Duration.standardDays(1).toMinutes(), equalTo(1440L));
    }
}