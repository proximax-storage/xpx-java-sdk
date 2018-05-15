package io.nem.xpx.facade;

import io.nem.xpx.callback.ServiceAsyncCallback;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


public abstract class AbstractAsyncFacadeService {

	protected <T, U> CompletableFuture<U> runAsync(final Function<T, U> facadeMethod, final T methodParameter,
												 final ServiceAsyncCallback<U> callback) {
		return CompletableFuture
				.supplyAsync(() -> facadeMethod.apply(methodParameter))
				.thenApply(result -> {
					callback.process(result);
					return result;
				});
	}
}
