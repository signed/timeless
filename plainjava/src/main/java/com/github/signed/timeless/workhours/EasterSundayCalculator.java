package com.github.signed.timeless.workhours;

import org.joda.time.LocalDate;

/**
 * Calculation as attributed to Spencer Jones
 */
public class EasterSundayCalculator {
    public LocalDate easterSundayFor(int year) {
        if (year < 1583) {
            throw new IllegalArgumentException();
        }
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int m = (32 + 2 * e + 2 * i - h - k) % 7;
        int n = (a + 11 * h + 22 * m) / 451;

        int dividend = (h + m - 7 * n + 114);
        int p = dividend / 31;
        int q = dividend % 31;

        return new LocalDate(year, p, q + 1);
    }
}
