package com.github.signed.timeless.storage;

public class HourAndMinute {

    public final int hour;
    public final int minute;

    public HourAndMinute(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static HourAndMinute parse(String timeString) {
        String[] split = timeString.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return new HourAndMinute(hour, minute);
    }

    public boolean earlierInDayThan(HourAndMinute other) {

        return this.hour < other.hour || this.hour == other.hour && this.minute < other.minute;
    }
}
