package com.github.signed.timeless.storage;

import com.github.signed.timeless.Punch;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.joda.time.DateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.any;

public class PunchMatcher extends TypeSafeMatcher<Punch> {

    private Matcher<Boolean> type = any(Boolean.class);
    private Matcher<DateTime> dateTimeMatcher = any(DateTime.class);

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
