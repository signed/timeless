package com.github.signed.timeless.time;

public interface TimeSource {
    LocalDate localDateNow();

    LocalDate localDateOf(int year, Month month, int day);

    //todo get rid of
    LocalDate localDateOf(org.joda.time.LocalDate localDate);
}
