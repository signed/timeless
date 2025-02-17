package com.github.signed.timeless.specialdays;

import java6.util.function.Predicate;
import org.joda.time.LocalDate;

@FunctionalInterface
public interface SpecialDay extends Predicate<LocalDate> {
}
