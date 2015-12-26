package com.github.signed.timeless.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;

public class BalanceSheet implements Iterable<BalanceRow>{
    private final List<BalanceRow> balanceRows;

    public BalanceSheet(List<BalanceRow> balanceRows) {
        this.balanceRows = new ArrayList<BalanceRow>(balanceRows);
        Collections.sort(balanceRows);
    }

    public void printToSystemOut() {
        for (BalanceRow balanceRow : balanceRows) {
            if (DateTimeConstants.MONDAY == balanceRow.day().dayOfWeek().get()) {
                System.out.println("");
            }
            String dayAsString = balanceRow.day().toString("E yyyy.MM.dd");
            System.out.println(dayAsString + ": " + balanceRow.balance().toPeriod().toString());
        }
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
