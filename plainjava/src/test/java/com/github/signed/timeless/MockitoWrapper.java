package com.github.signed.timeless;

import org.mockito.Mockito;

public class MockitoWrapper {
    public static void verifyZeroInteractions(Object... mocks) {
        Mockito.verifyNoMoreInteractions(mocks);
    }
}
