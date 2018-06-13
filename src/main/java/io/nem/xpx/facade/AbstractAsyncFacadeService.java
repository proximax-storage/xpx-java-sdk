package io.nem.xpx.facade;

import io.nem.xpx.callback.ServiceAsyncCallback;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;



/**
 * The Class AbstractAsyncFacadeService.
 */
public abstract class AbstractAsyncFacadeService {

	/**
	 * Run async.
	 *
	 * @param <T> the generic type
	 * @param facadeMethod the facade method
	 * @param callback the callback
	 * @return the completable future
	 */
	protected <T> CompletableFuture<T> runAsync(final Supplier<T> facadeMethod, final ServiceAsyncCallback<T> callback) {
		return CompletableFuture
				.supplyAsync(() -> facadeMethod.get())
				.thenApply(result -> {
					callback.process(result);
					return result;
				});
	}
}
