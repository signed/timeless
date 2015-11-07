package com.github.signed.timeless.balance;

import org.joda.time.Duration;

public class BalanceSheet {

    public final Duration requiredToWork;
    public final Duration timeWorked;
    public final Duration balance;

    public BalanceSheet(Duration requiredToWork, Duration timeWorked, Duration balance) {
        this.requiredToWork = requiredToWork;
        this.timeWorked = timeWorked;

        this.balance = balance;
    }
}
