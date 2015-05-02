package com.github.signed.timeless.storage;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeBuilder {

    public static DateTimeBuilder Year(int year) {
        return new DateTimeBuilder().year(year);
    }

    public static DateTimeBuilder AnyDateTime() {
        return Year(1976).december(2).at("15:30");
    }

    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int hourAdjust = 0;
    private int minutes;

    public DateTimeBuilder year(int year) {
        this.year = year;
        return this;
    }

    public DateTimeBuilder january(int day) {
        return month(1).dayOfMonth(day);
    }

    public DateTimeBuilder february(int day) {
        return month(2).dayOfMonth(day);
    }

    public DateTimeBuilder march(int day) {
        return month(3).dayOfMonth(day);
    }

    public DateTimeBuilder april(int day) {
        return month(4).dayOfMonth(day);
    }

    public DateTimeBuilder may(int day) {
        return month(5).dayOfMonth(day);
    }

    public DateTimeBuilder june(int day) {
        return month(6).dayOfMonth(day);
    }

    public DateTimeBuilder july(int day) {
        return month(7).dayOfMonth(day);
    }

    public DateTimeBuilder august(int day) {
        return month(8).dayOfMonth(day);
    }

    public DateTimeBuilder september(int day) {
        return month(9).dayOfMonth(day);
    }

    public DateTimeBuilder october(int day) {
        return month(10).dayOfMonth(day);
    }

    public DateTimeBuilder november(int day) {
        return month(11).dayOfMonth(day);
    }

    public DateTimeBuilder december(int day) {
        return month(12).dayOfMonth(day);
    }

    public DateTimeBuilder at(String timeString) {
        String[] split = timeString.split(":");
        hour = Integer.parseInt(split[0]);
        minutes = Integer.parseInt(split[1]);
        return this;
    }

    public DateTimeBuilder am() {
        this.hourAdjust = 0;
        return this;
    }

    public DateTimeBuilder pm() {
        this.hourAdjust = 12;
        return this;
    }

    public DateTime buildUtc() {
        return new DateTime(year, month, dayOfMonth, hour + hourAdjust, minutes, DateTimeZone.UTC);
    }

    private DateTimeBuilder month(int oneBasedMonth) {
        this.month = oneBasedMonth;
        return this;
    }

    private DateTimeBuilder dayOfMonth(int oneBasedDayOfTheMonth) {
        this.dayOfMonth = oneBasedDayOfTheMonth;
        return this;
    }

}
