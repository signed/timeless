package com.github.signed.timeless;

import org.joda.time.DateTime;

import java.util.Comparator;

public class Punch {

    private final boolean isIn;
    private final DateTime dateTime;

    private Punch(boolean isIn, DateTime dateTime) {
        this.isIn = isIn;
        this.dateTime = dateTime;
    }

    public static Comparator<Punch> compareByTimeStamp() {
        return new Comparator<>() {
            @Override
            public int compare(Punch o1, Punch o2) {
                return o1.dateTime().compareTo(o2.dateTime());
            }
        };
    }

    public static Punch PunchIn(DateTime dateTime) {
        return new Punch(true, dateTime);
    }

    public static Punch PunchOut(DateTime dateTime) {
        return new Punch(false, dateTime);
    }

    public DateTime dateTime() {
        return dateTime;
    }

    public boolean isIn() {
        return isIn;
    }
}
