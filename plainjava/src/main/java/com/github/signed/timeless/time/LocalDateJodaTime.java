package com.github.signed.timeless.time;

public class LocalDateJodaTime implements LocalDate {

    private final org.joda.time.LocalDate day;

    public LocalDateJodaTime(org.joda.time.LocalDate day) {
        this.day = day;
    }

    @Override
    public boolean is(Month month) {
        return day.monthOfYear().get() == ToJodaTime.toDateTimeConstant(month);
    }

    @Override
    public boolean isDayOfMonth(int dayOfMonth) {
        return day.dayOfMonth().get() == dayOfMonth;
    }

    @Override
    public LocalDate plusDays(int days) {
        return new LocalDateJodaTime(day.plusDays(days));
    }

    @Override
    public LocalDate minusDays(int days) {
        return new LocalDateJodaTime(day.minusDays(days));
    }

    @Override
    public DateTime toDateTimeAtStartOfDay(DateTimeZone zone) {
        var jodaTimeDateTimeZone = ToJodaTime.toJodaTime(zone);
        return new DateTimeJodaTime(day.toDateTimeAtStartOfDay(jodaTimeDateTimeZone));
    }

    @Override
    public DayOfWeek dayOfWeek() {
        return FromJodaTime.toDayOfWeek(day);
    }

    @Override
    public boolean isBefore(LocalDate localDate) {
        return day.isBefore(localDate.joda());
    }

    @Override
    public org.joda.time.LocalDate joda() {
        return day;
    }

    @Override
    public String asString() {
        return day.toString("E yyyy.MM.dd");
    }

    @Override
    public int weekOfWeekyear() {
        return day.getWeekOfWeekyear();
    }

    @Override
    public Month monthOfYear() {
        return FromJodaTime.toMonth(day);
    }

    @Override
    public int dayOfMonth() {
        return day.getDayOfMonth();
    }

    @Override
    public LocalDate plusMonths(int numberOfMonths) {
        return new LocalDateJodaTime(day.plusMonths(numberOfMonths));
    }

    @Override
    public int getYear() {
        return day.getYear();
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof LocalDateJodaTime jodaTime) {
            return this.day.equals(jodaTime.day);
        }
        return false;
    }

    @Override
    public int compareTo(LocalDate other) {
        return day.compareTo(other.joda());
    }

}
