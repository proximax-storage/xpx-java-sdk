package io.nem.xpx.builder.steps;


import java.net.URL;

/**
 * The Interface UrlStep.
 *
 * @param <T> the generic type
 */
public interface UrlStep<T> {
    
    /**
     * URL.
     *
     * @param url the url
     * @return the t
     */
    T url(URL url);
}
