package io.nem.xpx.utils;


/**
 * The Class ParameterValidationUtils.
 */
public class ParameterValidationUtils {

    /**
     * Instantiates a new parameter validation utils.
     */
    private ParameterValidationUtils() {
    }

    /**
     * Check parameter.
     *
     * @param isValid the is valid
     * @param invalidMessage the invalid message
     */
    public static void checkParameter(boolean isValid, String invalidMessage) {
        if (!isValid) throw new IllegalArgumentException(invalidMessage);
    }

}
