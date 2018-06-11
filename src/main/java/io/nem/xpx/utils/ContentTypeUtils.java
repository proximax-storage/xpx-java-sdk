package io.nem.xpx.utils;

import org.apache.tika.Tika;


/**
 * The Class ContentTypeUtils.
 */
public class ContentTypeUtils {

    /** The Constant TIKA. */
    private static final Tika TIKA = new Tika();

    /**
     * Content type lookup.
     *
     * @param contentType the content type
     * @param content the content
     * @return the string
     */
    public static String contentTypeLookup(final String contentType, final String content) {
        return contentType == null ||  contentType.equals("") ? TIKA.detect(content) : contentType;
    }

    /**
     * Content type lookup.
     *
     * @param contentType the content type
     * @param content the content
     * @return the string
     */
    public static String contentTypeLookup(final String contentType, final byte[] content) {
        return contentType == null ||  contentType.equals("") ? TIKA.detect(content) : contentType;
    }

    /**
     * Detect content type.
     *
     * @param content the content
     * @return the string
     */
    public static String detectContentType(final String content) {
        return content == null || content.equals("") ? null : TIKA.detect(content);
    }

    /**
     * Detect content type.
     *
     * @param content the content
     * @return the string
     */
    public static String detectContentType(final byte[] content) {
        return TIKA.detect(content);
    }
}
