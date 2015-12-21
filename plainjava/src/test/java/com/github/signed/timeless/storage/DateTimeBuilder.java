package com.github.signed.timeless.storage;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class DateTimeBuilder {

    public static DateTimeBuilder Year(int year) {
        return new DateTimeBuilder().year(year);
    }

    public static DateTimeBuilder AnyDateTime() {
        return Year(1976).december(2).at("15:30");
    }

    private DateTimeZone inputTimeZone = DateTimeZone.UTC;
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int hourAdjust = 0;
    private int minutes;

    public DateTimeBuilder withInputTimeZone(DateTimeZone inputTimeZone) {
        this.inputTimeZone = inputTimeZone;
        return this;
    }

    public DateTimeBuilder year(int year) {
        this.year = year;
        return this;
    }

    public DateTimeBuilder january(int day) {
        return month(1).the(day);
    }

    public DateTimeBuilder february(int day) {
        return month(2).the(day);
    }

    public DateTimeBuilder march(int day) {
        return month(3).the(day);
    }

    public DateTimeBuilder april(int day) {
        return april().the(day);
    }

    public DateTimeBuilder april() {
        return month(4);
    }

    public DateTimeBuilder may(int day) {
        return may().the(day);
    }

    public DateTimeBuilder may() {
        return month(5);
    }

    public DateTimeBuilder june(int day) {
        return june().the(day);
    }

    public DateTimeBuilder june() {
        return month(6);
    }

    public DateTimeBuilder july(int day) {
        return month(7).the(day);
    }

    public DateTimeBuilder july() {
        return month(7);
    }

    public DateTimeBuilder august(int day) {
        return month(8).the(day);
    }

    public DateTimeBuilder august() {
        return month(8);
    }

    public DateTimeBuilder september(int day) {
        return month(9).the(day);
    }

    public DateTimeBuilder september() {
        return month(9);
    }

    public DateTimeBuilder october(int day) {
        return month(10).the(day);
    }

    public DateTimeBuilder october() {
        return month(10);
    }


    public DateTimeBuilder november(int day) {
        return month(11).the(day);
    }

    public DateTimeBuilder november() {
        return month(11);
    }

    public DateTimeBuilder december(int day) {
        return month(12).the(day);
    }

    public DateTimeBuilder the(int oneBasedDayOfTheMonth) {
        this.dayOfMonth = oneBasedDayOfTheMonth;
        return this;
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

    public DateTimeBuilder copy() {
        DateTimeBuilder that = new DateTimeBuilder();
        that.year = this.year;
        that.month = this.month;
        that.dayOfMonth = this.dayOfMonth;
        that.hour = this.hour;
        that.hourAdjust = this.hourAdjust;
        that.minutes = this.minutes;
        return that;
    }

    public LocalDate buildDay(){
        return buildUtc().toLocalDate();
    }

    public DateTime buildUtc() {
        return new DateTime(year, month, dayOfMonth, hour + hourAdjust, minutes, inputTimeZone).withZone(DateTimeZone.UTC);
    }

    private DateTimeBuilder month(int oneBasedMonth) {
        this.month = oneBasedMonth;
        return this;
    }

}
