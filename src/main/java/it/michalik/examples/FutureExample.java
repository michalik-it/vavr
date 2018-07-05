package it.michalik.examples;

import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * A Future is a computation result that becomes available at some point.
 * All operations provided are non-blocking.
 * The underlying ExecutorService is used to execute asynchronous handlers, e.g. via onComplete(…​).
 */
public class FutureExample {
    private static Logger logger = Logger.getLogger(FutureExample.class);

    public static void main(String[] args) {
        someComputationBlockingConsumer();
//        someComputationThatFailsBlockingConsumer();
//        someComputationNonBlockingConsumer();
//        someComputationThatFailsNonBlockingConsumer();
    }

    private static String someComputation() {
        logger.info("start computation");
        sleep(100);
        logger.info("end computation");
        return "long time computed string";
    }

    private static void someComputationBlockingConsumer() {
        logger.info("start main");
        Future<String> resultFuture = Future.of(() -> someComputation());

        //blocking
        String result = resultFuture.getOrElse("Failed to get underlying value.");
        logger.info(result);

    }

    private static void someComputationNonBlockingConsumer() {
        logger.info("start main");
        Future<String> resultFuture = Future.of(() -> someComputation());

        //blocking
        logger.info("isCompleted:" + resultFuture.isCompleted());
        logger.info("isSuccess:" + resultFuture.isSuccess());

        sleep(200);
        logger.info("isCompleted:" + resultFuture.isCompleted());
        logger.info("isSuccess:" + resultFuture.isSuccess());

        resultFuture
                .onSuccess(v -> logger.info("Successfully Completed - Result: " + v))
                .onFailure(v -> logger.info("Failed - Result: " + v));
    }

    private static String someComputationThatFails() {
        logger.info("start computation");
        sleep(100);
        throw new RuntimeException("Faild");
    }

    private static void sleep(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void someComputationThatFailsBlockingConsumer() {
        logger.info("start main");
        Future<String> resultFuture = Future.of(() -> someComputationThatFails());

        //blocking
        String result = resultFuture.getOrElse("Failed to get underlying value.");
        logger.info(result);
    }

    private static void someComputationThatFailsNonBlockingConsumer() {
        logger.info("start main");
        Future<String> resultFuture = Future.of(() -> someComputationThatFails());

        //blocking
        logger.info("isCompleted:" + resultFuture.isCompleted());
        logger.info("isSuccess:" + resultFuture.isSuccess());

        sleep(200);
        logger.info("isCompleted:" + resultFuture.isCompleted());
        logger.info("isSuccess:" + resultFuture.isSuccess());
        logger.info("Error message: " + resultFuture.getCause().get().getMessage());
    }
}
