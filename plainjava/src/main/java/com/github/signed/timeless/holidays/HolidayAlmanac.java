package com.github.signed.timeless.holidays;

import java.util.List;

interface HolidayAlmanac {
    List<Holiday> holidaysFor(int year);
}
