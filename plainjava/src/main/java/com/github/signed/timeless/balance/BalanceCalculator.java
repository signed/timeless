package com.github.signed.timeless.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.Punch;
import com.github.signed.timeless.TimeCard;

public class BalanceCalculator {

    private final HoursRequired hoursRequired;

    public BalanceCalculator(HoursRequired hoursRequired) {
        this.hoursRequired = hoursRequired;
    }

    public BalanceSheet balanceFor(TimeCard timeCard) {
        Map<LocalDate, List<Punch>> punchesPerDay = new HashMap<LocalDate, List<Punch>>();

        for (Punch punch : timeCard) {
            LocalDate day = punch.dateTime().toLocalDate();

            List<Punch> punchesAtCurrentDay = punchesPerDay.get(day);
            if (null == punchesAtCurrentDay) {
                punchesAtCurrentDay = new ArrayList<Punch>();
                punchesPerDay.put(day, punchesAtCurrentDay);
            }
            punchesAtCurrentDay.add(punch);
        }

        Duration timeWorked = Duration.millis(0);
        List<DailyWorkLog> workLogs = new ArrayList<DailyWorkLog>();
        for (Map.Entry<LocalDate, List<Punch>> entry : punchesPerDay.entrySet()) {
            DailyWorkLog workLog = new DailyWorkLog(entry.getKey(), entry.getValue());
            timeWorked = timeWorked.plus(workLog.timeWorked());
            workLogs.add(workLog);
        }

        printToSystemOut(workLogs);

        Duration requiredToWork = Duration.ZERO;
        for (Map.Entry<LocalDate, List<Punch>> punch : punchesPerDay.entrySet()) {
            requiredToWork = requiredToWork.plus(this.hoursRequired.hoursRequiredAt(punch.getKey()));
        }

        Duration balance = timeWorked.minus(requiredToWork);

        return new BalanceSheet(requiredToWork, timeWorked, balance);
    }

    private void printToSystemOut(List<DailyWorkLog> workLogs) {
        Collections.sort(workLogs, new Comparator<DailyWorkLog>() {
            @Override
            public int compare(DailyWorkLog o1, DailyWorkLog o2) {
                return o1.day.compareTo(o2.day);
            }
        });

        for (DailyWorkLog workLog : workLogs) {
            if (DateTimeConstants.MONDAY == workLog.day.dayOfWeek().get()) {
                System.out.println("");
            }
            String dayAsString = workLog.day.toString("E yyyy.MM.dd");
            System.out.println(dayAsString +": "+ workLog.timeWorked().minus(Duration.standardHours(8)).toPeriod().toString());
        }
    }
}
