package com.github.signed.timeless.balance;

import org.joda.time.Duration;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class WeeklyBalance implements Iterable<BalanceRow> {
    private final List<BalanceRow> balanceRows;

    WeeklyBalance(List<BalanceRow> balanceRows) {
        this.balanceRows = balanceRows;
    }

    public Duration requiredToWork() {
        Duration requiredToWork = Duration.ZERO;
        for (BalanceRow balanceRow : balanceRows) {
            requiredToWork = requiredToWork.plus(balanceRow.requiredToWork());
        }
        return requiredToWork;
    }

    public Duration timeWorked() {
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
