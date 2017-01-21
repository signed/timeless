package com.github.signed.timeless.holidays;

import java.util.List;

public interface HolidayAlmanac {
    List<Holiday> holidaysFor(int year);
}
