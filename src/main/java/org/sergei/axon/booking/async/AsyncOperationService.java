package org.sergei.axon.booking.async;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperationService {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncOperationService.class);

    @Async("asyncExecutor")
    public CompletableFuture<String> uppercaseString(String someString) {

        LOG.info("Started...");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        LOG.info("Proceeding...");

        return CompletableFuture.completedFuture(someString.toUpperCase());
    }

    @Async("asyncExecutor")
    public CompletableFuture<String> substringString(String someString) {

        LOG.info("Started...");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        LOG.info("Proceeding...");

        return CompletableFuture.completedFuture(someString.substring(0, 3));
    }

    @Async("asyncExecutor")
    public CompletableFuture<String> appendString(String someString) {

        LOG.info("Started...");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        LOG.info("Proceeding...");

        return CompletableFuture.completedFuture(someString + "BBB");
    }

}
