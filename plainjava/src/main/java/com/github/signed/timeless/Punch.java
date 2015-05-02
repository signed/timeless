package com.github.signed.timeless;

import org.joda.time.DateTime;

public class Punch {

    public static Punch PunchIn(DateTime dateTime) {
        return new Punch(true, dateTime);
    }

    public static Punch PunchOut(DateTime dateTime) {
        return new Punch(false, dateTime);
    }

    private final boolean isIn;
    private final DateTime dateTime;

    public Punch(boolean isIn, DateTime dateTime) {
        this.isIn = isIn;
        this.dateTime = dateTime;
    }

    public DateTime dateTime() {
        return dateTime;
    }

    public boolean isIn() {
        return isIn;
    }
}
