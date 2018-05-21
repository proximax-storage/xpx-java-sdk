package io.nem.xpx.facade;

import io.nem.xpx.callback.ServiceAsyncCallback;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


public abstract class AbstractAsyncFacadeService {

	protected <T> CompletableFuture<T> runAsync(final Supplier<T> facadeMethod, final ServiceAsyncCallback<T> callback) {
		return CompletableFuture
				.supplyAsync(() -> facadeMethod.get())
				.thenApply(result -> {
					callback.process(result);
					return result;
				});
	}
}
