package com.github.signed.timeless.storage;

import com.github.signed.timeless.time.DateTime;
import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.FromJodaTime;
import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;

import static com.github.signed.timeless.time.Month.April;
import static com.github.signed.timeless.time.Month.August;
import static com.github.signed.timeless.time.Month.December;
import static com.github.signed.timeless.time.Month.February;
import static com.github.signed.timeless.time.Month.January;
import static com.github.signed.timeless.time.Month.July;
import static com.github.signed.timeless.time.Month.June;
import static com.github.signed.timeless.time.Month.March;
import static com.github.signed.timeless.time.Month.May;
import static com.github.signed.timeless.time.Month.November;
import static com.github.signed.timeless.time.Month.October;
import static com.github.signed.timeless.time.Month.September;

public class DateTimeBuilder {

    private DateTimeZone inputTimeZone = DateTimeZone.Utc;
    private int year;
    private Month month;
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
        return new DateTimeBuilder().year(day.getYear()).month(day.monthOfYear()).the(day.dayOfMonth());
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
        return month(January);
    }

    public DateTimeBuilder february(int day) {
        return february().the(day);
    }

    public DateTimeBuilder february() {
        return month(February);
    }

    public DateTimeBuilder march(int day) {
        return march().the(day);
    }

    public DateTimeBuilder march() {
        return month(March);
    }

    public DateTimeBuilder april(int day) {
        return april().the(day);
    }

    public DateTimeBuilder april() {
        return month(April);
    }

    public DateTimeBuilder may(int day) {
        return may().the(day);
    }

    public DateTimeBuilder may() {
        return month(May);
    }

    public DateTimeBuilder june(int day) {
        return june().the(day);
    }

    public DateTimeBuilder june() {
        return month(June);
    }

    public DateTimeBuilder july(int day) {
        return july().the(day);
    }

    public DateTimeBuilder july() {
        return month(July);
    }

    public DateTimeBuilder august(int day) {
        return august().the(day);
    }

    public DateTimeBuilder august() {
        return month(August);
    }

    public DateTimeBuilder september(int day) {
        return september().the(day);
    }

    public DateTimeBuilder september() {
        return month(September);
    }

    public DateTimeBuilder october(int day) {
        return october().the(day);
    }

    public DateTimeBuilder october() {
        return month(October);
    }

    public DateTimeBuilder november(int day) {
        return november().the(day);
    }

    public DateTimeBuilder november() {
        return month(November);
    }

    private DateTimeBuilder december(int day) {
        return december().the(day);
    }

    public DateTimeBuilder december() {
        return month(December);
    }

    public DateTimeBuilder on(LocalDate day) {
        return year(day.getYear()).month(day.monthOfYear()).the(day.dayOfMonth());
    }

    private DateTimeBuilder month(int jodaMonthConstant) {
        return month(FromJodaTime.toMonth(jodaMonthConstant));
    }

    private DateTimeBuilder month(Month month) {
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
        return DateTime.of(year, month, dayOfMonth, hour, minutes, inputTimeZone).withZone(DateTimeZone.Utc).plusDays(dayAdjust);
    }
}
