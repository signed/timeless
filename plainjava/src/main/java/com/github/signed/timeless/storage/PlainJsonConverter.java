package com.github.signed.timeless.storage;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.github.signed.timeless.Punch;
import com.google.gson.Gson;

public class PlainJsonConverter {
    private static final int Version = 1;
    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendYear(4, 4).appendMonthOfYear(2).appendDayOfMonth(2).appendHourOfDay(2).appendMinuteOfHour(2).toFormatter().withZoneUTC();

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
            destination.add(punch.dateTime().toString(formatter));
        }

        StorageRepresentation src = new StorageRepresentation();
        src.punch_in = punchIn.toArray(new String[punchIn.size()]);
        src.punch_out = punchOut.toArray(new String[punchOut.size()]);

        return gson().toJson(src);
    }

    public Collection<Punch> deSerialize(String json) {
        List<Punch> result = new LinkedList<Punch>();
        StorageRepresentation storageRepresentation = gson().fromJson(json, StorageRepresentation.class);
        for (String time : storageRepresentation.punch_in) {
            result.add(Punch.PunchIn(formatter.parseDateTime(time)));
        }
        for (String time : storageRepresentation.punch_out) {
            result.add(Punch.PunchOut(formatter.parseDateTime(time)));
        }
        return result;
    }

    private Gson gson() {
        return new Gson();
    }
}
