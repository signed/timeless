package com.github.signed.timeless.storage;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import static org.joda.time.DateTimeConstants.*;

public class DateTimeBuilder {

    private DateTimeZone inputTimeZone = DateTimeZone.UTC;
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int dayAdjust = 0;
    private int minutes;

    public static DateTimeBuilder Year(int year) {
        return new DateTimeBuilder().year(year);
    }

    public static DateTimeBuilder AnyDateTime() {
        return Year(1976).december(2).hour(15).minute(30);
    }

    public static DateTimeBuilder At(LocalDate day) {
        return new DateTimeBuilder().year(day.getYear()).month(day.getMonthOfYear()).the(day.getDayOfMonth());
    }

    public DateTimeBuilder withInputTimeZone(DateTimeZone inputTimeZone) {
        this.inputTimeZone = inputTimeZone;
        return this;
    }

    private DateTimeBuilder year(int year) {
        this.year = year;
        return this;
    }

    public DateTimeBuilder january(int day) {
        return january().the(day);
    }

    public DateTimeBuilder january() {
        return month(JANUARY);
    }

    public DateTimeBuilder february(int day) {
        return february().the(day);
    }

    public DateTimeBuilder february() {
        return month(FEBRUARY);
    }

    public DateTimeBuilder march(int day) {
        return march().the(day);
    }

    public DateTimeBuilder march() {
        return month(MARCH);
    }

    public DateTimeBuilder april(int day) {
        return april().the(day);
    }

    public DateTimeBuilder april() {
        return month(APRIL);
    }

    public DateTimeBuilder may(int day) {
        return may().the(day);
    }

    public DateTimeBuilder may() {
        return month(MAY);
    }

    public DateTimeBuilder june(int day) {
        return june().the(day);
    }

    public DateTimeBuilder june() {
        return month(JUNE);
    }

    public DateTimeBuilder july(int day) {
        return july().the(day);
    }

    public DateTimeBuilder july() {
        return month(DateTimeConstants.JULY);
    }

    public DateTimeBuilder august(int day) {
        return august().the(day);
    }

    public DateTimeBuilder august() {
        return month(AUGUST);
    }

    public DateTimeBuilder september(int day) {
        return september().the(day);
    }

    public DateTimeBuilder september() {
        return month(SEPTEMBER);
    }

    public DateTimeBuilder october(int day) {
        return october().the(day);
    }

    public DateTimeBuilder october() {
        return month(OCTOBER);
    }

    public DateTimeBuilder november(int day) {
        return november().the(day);
    }

    public DateTimeBuilder november() {
        return month(NOVEMBER);
    }

    private DateTimeBuilder december(int day) {
        return december().the(day);
    }

    public DateTimeBuilder december() {
        return month(DECEMBER);
    }

    public DateTimeBuilder on(LocalDate day) {
        return year(day.getYear()).month(day.getMonthOfYear()).the(day.getDayOfMonth());
    }

    private DateTimeBuilder month(int month) {
        this.month = month;
        return this;
    }

    public DateTimeBuilder the(int oneBasedDayOfTheMonth) {
        this.dayOfMonth = oneBasedDayOfTheMonth;
        return this;
    }

    public DateTimeBuilder at(HourAndMinute hourAndMinute) {
        hour(hourAndMinute.hour);
        minute(hourAndMinute.minute);
        return this;
    }

    public DateTimeBuilder nextDay() {
        this.dayAdjust += 1;
        return this;
    }

    public DateTimeBuilder hour(int hour) {
        this.hour = hour;
        return this;
    }

    private DateTimeBuilder minute(int minute) {
        this.minutes = minute;
        return this;
    }

    public DateTimeBuilder copy() {
        DateTimeBuilder that = new DateTimeBuilder();
        that.year = this.year;
        that.month = this.month;
        that.dayOfMonth = this.dayOfMonth;
        that.hour = this.hour;
        that.minutes = this.minutes;
        return that;
    }

    public LocalDate buildDay() {
        return buildUtc().toLocalDate();
    }

    public DateTime buildUtc() {
        return new DateTime(year, month, dayOfMonth, hour, minutes, inputTimeZone).withZone(DateTimeZone.UTC).plusDays(dayAdjust);
    }
}
