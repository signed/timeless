package com.github.signed.timeless.storage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.any;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.joda.time.DateTime;

import com.github.signed.timeless.Punch;

public class PunchMatcher extends TypeSafeMatcher<Punch> {

    public static PunchMatcher PunchIn() {
        PunchMatcher punchMatcher = new PunchMatcher();
        punchMatcher.type = is(true);
        return punchMatcher;
    }

    public static PunchMatcher PunchOut() {
        PunchMatcher punchMatcher = new PunchMatcher();
        punchMatcher.type = is(false);
        return punchMatcher;
    }

    private Matcher<Boolean> type = any(Boolean.class);
    private Matcher<DateTime> dateTimeMatcher = any(DateTime.class);

    public PunchMatcher at(DateTime dateTime) {
        dateTimeMatcher = is(dateTime);
        return this;
    }

    @Override
    public void describeTo(Description description) {
        type.describeTo(description);
        dateTimeMatcher.describeTo(description);
    }

    @Override
    protected void describeMismatchSafely(Punch item, Description mismatchDescription) {
        if (!type.matches(item.isIn())) {
            type.describeMismatch(item.isIn(), mismatchDescription);
        }

        if (!dateTimeMatcher.matches(item)) {
            dateTimeMatcher.describeMismatch(item.dateTime(), mismatchDescription);
        }
    }

    @Override
    protected boolean matchesSafely(Punch item) {
        boolean typeMatches = type.matches(item.isIn());
        boolean dateTimeMatches = dateTimeMatcher.matches(item.dateTime());
        return typeMatches && dateTimeMatches;
    }
}
