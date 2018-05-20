package io.nem.xpx.utils;

public class ParameterValidationUtils {

    private ParameterValidationUtils() {
    }

    public static void checkParameter(boolean isValid, String invalidMessage) {
        if (!isValid) throw new IllegalArgumentException(invalidMessage);
    }

}
