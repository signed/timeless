package com.github.signed.timeless.storage;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.format.DateTimeFormatterBuilder;

import com.github.signed.timeless.Punch;
import com.google.gson.Gson;

public class PlainJsonConverter {
    private static final int Version = 1;

    private static class StorageRepresentation {
        public int version = Version;
        public String[] punch_in;
        public String[] punch_out;
    }

    public String serialize(Collection<Punch> punches) {
        List<String> punchIn = new LinkedList<String>();
        List<String> punchOut = new LinkedList<String>();

        for (Punch punch : punches) {
            List<String> destination = punchOut;
            if (punch.isIn()) {
                destination = punchIn;
            }
            destination.add(punch.dateTime().toString(new DateTimeFormatterBuilder().appendYear(4, 4).appendMonthOfYear(2).appendDayOfMonth(2).appendHourOfDay(2).appendMinuteOfHour(2).toFormatter()));
        }

        StorageRepresentation src = new StorageRepresentation();
        src.punch_in = punchIn.toArray(new String[punchIn.size()]);
        src.punch_out = punchOut.toArray(new String[punchOut.size()]);

        return new Gson().toJson(src);
    }

    public Collection<Punch> deSerialize(String json) {
        return null;
    }
}
