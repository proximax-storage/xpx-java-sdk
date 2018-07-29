/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.utils;

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
        return StringUtils.isEmpty(contentType) ? TIKA.detect(content) : contentType;
    }

    /**
     * Content type lookup.
     *
     * @param contentType the content type
     * @param content the content
     * @return the string
     */
    public static String contentTypeLookup(final String contentType, final byte[] content) {
        return StringUtils.isEmpty(contentType)  ? TIKA.detect(content) : contentType;
    }

    /**
     * Detect content type.
     *
     * @param content the content
     * @return the string
     */
    public static String detectContentType(final String content) {
        return StringUtils.isEmpty(content) ? null : TIKA.detect(content);
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
