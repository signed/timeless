package com.github.signed.timeless.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.Punch;
import com.github.signed.timeless.TimeCard;
import com.github.signed.timeless.workhours.WorkHoursPerDay;

public class BalanceCalculator {

    private static class BalanceRow implements Comparable<BalanceRow>{
        private final LocalDate day;
        private final WorkHoursPerDay workHoursPerDay;
        private final DailyWorkLog dailyWorkLog;

        private BalanceRow(LocalDate day, WorkHoursPerDay workHoursPerDay, DailyWorkLog dailyWorkLog) {
            this.day = day;
            this.workHoursPerDay = workHoursPerDay;
            this.dailyWorkLog = dailyWorkLog;
        }

        public Duration balance(){
            return dailyWorkLog.timeWorked().minus(workHoursPerDay.duration());
        }

        @Override
        public int compareTo(BalanceRow o) {
            return day.compareTo(o.day);
        }
    }

    private final HoursRequired hoursRequired;

    public BalanceCalculator(HoursRequired hoursRequired) {
        this.hoursRequired = hoursRequired;
    }

    public BalanceSheet balanceFor(TimeCard timeCard) {
        Map<LocalDate, List<Punch>> punchesPerDay = timeCard.punchesPerDay();
        Duration timeWorked = Duration.millis(0);
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
            timeWorked = timeWorked.plus(dailyWorkLog.timeWorked());
            balanceRows.add(new BalanceRow(day, workHoursPerDay, dailyWorkLog));
        }

        printToSystemOut(balanceRows);

        Duration balance = timeWorked.minus(requiredToWork);
        return new BalanceSheet(requiredToWork, timeWorked, balance);
    }

    private void printToSystemOut(List<BalanceRow> balanceRows) {
        Collections.sort(balanceRows);

        for (BalanceRow balanceRow : balanceRows) {
            if (DateTimeConstants.MONDAY == balanceRow.day.dayOfWeek().get()) {
                System.out.println("");
            }
            String dayAsString = balanceRow.day.toString("E yyyy.MM.dd");
            System.out.println(dayAsString + ": " + balanceRow.balance().toPeriod().toString());
        }
    }
}
