package com.github.signed.timeless.time;

public interface LocalDateFactory {
    LocalDate now();

    LocalDate of(int year, Month month, int day);

    //todo get rid of
    LocalDate of(org.joda.time.LocalDate localDate);
}
