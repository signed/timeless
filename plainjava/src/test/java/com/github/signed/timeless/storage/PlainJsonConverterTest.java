package com.github.signed.timeless.storage;

import static com.github.signed.timeless.storage.PlainJsonConverterTest.DateTimeBuilder.Year;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Test;

import com.github.signed.timeless.Punch;
import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class PlainJsonConverterTest {
    private final Collection<Punch> punches = new ArrayList<Punch>();

    @Test
    public void serializeVersionNumber() throws Exception {
        serializedJson().assertThat("$.version", is(1));
    }


    public static class DateTimeBuilder {

        public static DateTimeBuilder Year(int year) {
            return new DateTimeBuilder().year(year);
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
            return new DateTime(year, month, dayOfMonth, hour + hourAdjust, minutes);
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

    @Test
    public void serializePunchIn() throws Exception {
        punchInAt(Year(2015).january(7).at("9:30").pm());

        serializedJson().assertThat("$.punch_in[0]", is("201501072130"));
    }

    @Test
    public void serializePunchOut() throws Exception {
        punchOutAt(Year(2013).february(21).at("9:30").am());

        serializedJson().assertThat("$.punch_out[0]", is("201302210930"));
    }

    private void punchInAt(DateTimeBuilder dateTime) {
        punches.add(Punch.PunchIn(dateTime.buildUtc()));
    }

    private void punchOutAt(DateTimeBuilder dateTime) {
        punches.add(Punch.PunchOut(dateTime.buildUtc()));
    }

    private JsonAsserter serializedJson() {
        String json = new PlainJsonConverter().serialize(punches);
        return JsonAssert.with(json);
    }
}