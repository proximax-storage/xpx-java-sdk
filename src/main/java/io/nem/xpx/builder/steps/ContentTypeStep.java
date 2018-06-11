package io.nem.xpx.builder.steps;


/**
 * The Interface ContentTypeStep.
 *
 * @param <T> the generic type
 */
public interface ContentTypeStep<T> {
    
    /**
     * Content type.
     *
     * @param contentType the content type
     * @return the t
     */
    T contentType(String contentType);
}
