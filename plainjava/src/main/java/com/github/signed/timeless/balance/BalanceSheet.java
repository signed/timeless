package com.github.signed.timeless.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.joda.time.Duration;

public class BalanceSheet implements Iterable<BalanceRow>{
    private final List<BalanceRow> balanceRows;

    BalanceSheet(List<BalanceRow> balanceRows) {
        this.balanceRows = new ArrayList<>(balanceRows);
        Collections.sort(balanceRows);
    }

    public Duration requiredToWork(){
        Duration requiredToWork = Duration.ZERO;
        for (BalanceRow balanceRow : balanceRows) {
            requiredToWork = requiredToWork.plus(balanceRow.requiredToWork());
        }
        return requiredToWork;
    }

    public Duration timeWorked(){
        Duration timeWorked = Duration.ZERO;
        for (BalanceRow balanceRow : balanceRows) {
            timeWorked = timeWorked.plus(balanceRow.timeWorked());
        }
        return timeWorked;
    }

    Iterable<WeeklyBalance> weeklyBalance(){
        int currentWeekOfYear = Integer.MIN_VALUE;
        ArrayList<WeeklyBalance> weeklyBalances = new ArrayList<>();
        List<BalanceRow> currentWeekBalanceRows = new ArrayList<>();

        for (BalanceRow balanceRow : balanceRows) {
            int weekOfWeekyear = balanceRow.day().getWeekOfWeekyear();
            if (currentWeekOfYear != weekOfWeekyear) {
                currentWeekOfYear = weekOfWeekyear;
                if(!currentWeekBalanceRows.isEmpty()){
                    weeklyBalances.add(new WeeklyBalance(currentWeekBalanceRows));
                    currentWeekBalanceRows = new ArrayList<>();
                }
            }
            currentWeekBalanceRows.add(balanceRow);
        }

        if(!currentWeekBalanceRows.isEmpty()){
            weeklyBalances.add(new WeeklyBalance(currentWeekBalanceRows));
        }

        return weeklyBalances;
    }

    public Duration balance() {
        Duration balance = Duration.ZERO;
        for (BalanceRow balanceRow : balanceRows) {
            balance = balance.plus(balanceRow.balance());
        }
        return balance;
    }

    @Override
    public Iterator<BalanceRow> iterator() {
        return Collections.unmodifiableList(balanceRows).iterator();
    }
}
