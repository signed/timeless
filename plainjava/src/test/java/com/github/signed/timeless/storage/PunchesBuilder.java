package com.github.signed.timeless.storage;

import java.util.ArrayList;
import java.util.Collection;

import com.github.signed.timeless.Punch;

public class PunchesBuilder {
    private final Collection<Punch> punches = new ArrayList<Punch>();

    public void punchInAt(DateTimeBuilder dateTime) {
        punches.add(Punch.PunchIn(dateTime.buildUtc()));
    }

    public void punchOutAt(DateTimeBuilder dateTime) {
        punches.add(Punch.PunchOut(dateTime.buildUtc()));
    }

    public ArrayList<Punch> punches(){
        return new ArrayList<Punch>(punches);
    }

}
