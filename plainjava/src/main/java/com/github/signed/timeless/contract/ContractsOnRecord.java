package com.github.signed.timeless.contract;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.LocalDate;

import java.util.List;

public class ContractsOnRecord implements WorkHoursPerDayAdjuster {
    private final List<Contract> contracts;

    public ContractsOnRecord(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        final var first = contracts.stream().filter(contract -> contract.active(date)).findFirst();
        first.ifPresent(contract -> contract.adjustHoursToWorkFor(date, workHoursPerDayBuilder));
    }
}
