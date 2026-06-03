package com.github.signed.timeless.time;

/**
 * Mirrors import com.github.signed.timeless.time.LocalDate;
 */
public interface LocalDate {

    boolean is(Month month);

    boolean isDayOfMonth(int dayOfMonth);

}
