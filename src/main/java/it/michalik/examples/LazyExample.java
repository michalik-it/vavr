package it.michalik.examples;

import io.vavr.Lazy;
import org.apache.log4j.Logger;

/**
 * Lazy is a container type which represents a lazy evaluated value. It evaluates only once and therefore is referentially transparent.
 * Since version 2.0.0 you may also create a real lazy value (works only with interfaces)
 */
public class LazyExample {
    private static Logger logger = Logger.getLogger(LazyExample.class);

    public static void main(String[] args) {
        //lazyExample();


        //only interface
        lazyOnlyInterfaceExample();
    }

    private static void lazyOnlyInterfaceExample() {
        CharSequence chars = Lazy.val(() -> {
            System.out.println("calculated");
            return "Yay! ";
        }, CharSequence.class);

        logger.info("Magic is happening now");
        logger.info(chars);
        logger.info(chars);
        logger.info(chars);
        logger.info(chars);
    }

    private static void lazyExample() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        logger.info(lazy.isEvaluated());
        logger.info(lazy.get());
        logger.info(lazy.isEvaluated());
        logger.info(lazy.get());
    }
}
