package io.nem.xpx.utils;

import org.apache.tika.Tika;

public class ContentTypeUtils {

    private static final Tika TIKA = new Tika();

    public static String contentTypeLookup(final String contentType, final String content) {
        return contentType == null ||  contentType.equals("") ? TIKA.detect(content) : contentType;
    }

    public static String contentTypeLookup(final String contentType, final byte[] content) {
        return contentType == null ||  contentType.equals("") ? TIKA.detect(content) : contentType;
    }

    public static String detectContentType(final String content) {
        return content == null || content.equals("") ? null : TIKA.detect(content);
    }

    public static String detectContentType(final byte[] content) {
        return TIKA.detect(content);
    }
}
