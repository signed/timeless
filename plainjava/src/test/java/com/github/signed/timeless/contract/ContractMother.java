package com.github.signed.timeless.contract;

import org.jspecify.annotations.NonNull;

import java.util.Arrays;

import static com.github.signed.timeless.contract.Contract.infinity;

public class ContractMother {
    public static @NonNull Contract fiveHoursEveryDayNoHolidays() {
        return new Contract(infinity(), Arrays.asList(WeeklyWorkHours.sevenDayWorkWeekOf(35)));
    }
}
