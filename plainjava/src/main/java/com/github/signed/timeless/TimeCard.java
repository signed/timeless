package com.github.signed.timeless;

import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.signed.timeless.Constants.backendTimeZone;
import static com.github.signed.timeless.Punch.compareByTimeStamp;

public class TimeCard {

    private final Interval intervalCovered;
    private final List<ConsecutiveTime> consecutiveTimes;

    public TimeCard(Interval intervalCovered, List<Punch> punches) {
        this.intervalCovered = intervalCovered;
        List<Punch> sortedPunches = new ArrayList<>(punches);
        Collections.sort(sortedPunches, compareByTimeStamp());
        consecutiveTimes = toConsecutiveTimes(sortedPunches);
    }

    private static List<ConsecutiveTime> toConsecutiveTimes(List<Punch> sortedPunches) {
        List<ConsecutiveTime> result = new ArrayList<>();
        for (int i = 0; i < sortedPunches.size(); i += 2) {
            Punch start = sortedPunches.get(i);
            Punch stop = sortedPunches.get(i + 1);
            result.add(new ConsecutiveTime(start, stop));
        }
        return result;
    }

    public LocalDate from() {
        return intervalCovered.getStart().toLocalDate();
    }

    public boolean covers(LocalDate day) {
        return intervalCovered.contains(day.toDateTimeAtStartOfDay(backendTimeZone()));
    }

    public List<ConsecutiveTime> consecutiveTimesOverlapping(Interval workday) {
        List<ConsecutiveTime> result = new ArrayList<>();
        for (ConsecutiveTime candidate : consecutiveTimes) {
            if (candidate.overlapsWith(workday)) {
                result.add(candidate);
            }
        }
        return result;
    }
}
