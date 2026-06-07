package com.vocallabs.outreach.util;

import java.util.function.Supplier;

public class RetryUtil {

    public static <T> T execute(Supplier<T> action, int retryCount) {

        int attempt = 0;

        while (attempt < retryCount) {
            try {
                return action.get();
            } catch (Exception e) {
                attempt++;
                if (attempt == retryCount) {
                    throw e;
                }
            }
        }

        return null;
    }
}