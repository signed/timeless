package com.github.signed.timeless.storage;

import static com.github.signed.timeless.storage.DateTimeBuilder.Year;
import static com.github.signed.timeless.storage.PunchMatcher.PunchIn;
import static com.github.signed.timeless.storage.PunchMatcher.PunchOut;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class PlainJsonConverterTest {

    private final PunchesBuilder punches = new PunchesBuilder();

    private PlainJsonConverter converter = new PlainJsonConverter();

    @Test
    public void serializeVersionNumber() throws Exception {
        serializedJson().assertThat("$.version", is(1));
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

    @Test
    public void roundTripTest() throws Exception {
        DateTimeBuilder builder = DateTimeBuilder.AnyDateTime();
        DateTime dateTime = builder.buildUtc();
        punchInAt(builder);
        punchOutAt(builder);

        assertThat(converter.deSerialize(punchesToJson()), hasItems(PunchIn().at(dateTime), PunchOut().at(dateTime)));
    }

    private void punchOutAt(DateTimeBuilder am) {
        punches.punchOutAt(am);
    }

    private void punchInAt(DateTimeBuilder pm) {
        punches.punchInAt(pm);
    }

    private JsonAsserter serializedJson() {
        String json = punchesToJson();
        return JsonAssert.with(json);
    }

    private String punchesToJson() {
        return converter.serialize(punches.punches());
    }

}