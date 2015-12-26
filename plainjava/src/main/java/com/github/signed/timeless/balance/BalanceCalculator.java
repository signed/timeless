package com.github.signed.timeless.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.Punch;
import com.github.signed.timeless.TimeCard;
import com.github.signed.timeless.workhours.WorkHoursPerDay;

public class BalanceCalculator {

    private final HoursRequired hoursRequired;

    public BalanceCalculator(HoursRequired hoursRequired) {
        this.hoursRequired = hoursRequired;
    }

    public BalanceSheet balanceFor(TimeCard timeCard) {
        Map<LocalDate, List<Punch>> punchesPerDay = timeCard.punchesPerDay();
        List<BalanceRow> balanceRows = new ArrayList<BalanceRow>();

        Duration requiredToWork = Duration.ZERO;
        for(LocalDate day = timeCard.from(); !timeCard.until().isBefore(day); day = day.plusDays(1) ){
            WorkHoursPerDay workHoursPerDay = this.hoursRequired.hoursToWorkAt(day);
            requiredToWork = requiredToWork.plus(workHoursPerDay.duration());
            List<Punch> punches = punchesPerDay.get(day);
            if (punches == null) {
                punches = Collections.emptyList();
            }
            DailyWorkLog dailyWorkLog = new DailyWorkLog(day, punches);
            balanceRows.add(new BalanceRow(day, workHoursPerDay, dailyWorkLog));
        }
        return new BalanceSheet(balanceRows);
    }

}
