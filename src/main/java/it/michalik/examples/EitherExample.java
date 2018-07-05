package it.michalik.examples;

import io.vavr.control.Either;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Either represents a value of two possible types. An Either is either a Left or a Right
 */
public class EitherExample {

    private static Logger logger = Logger.getLogger(EitherExample.class);

    /**
     * PLAIN JAVA
     */
    public static Map<String, Object> computeWithoutEitherUsingMapJava(int marks) {
        Map<String, Object> results = new HashMap<>();
        if (marks < 85) {
            results.put("FAILURE", "Marks not acceptable");
        } else {
            results.put("SUCCESS", marks);
        }
        return results;
    }

    public static void plainJavaConsumer() {
        Map<String, Object> results = computeWithoutEitherUsingMapJava(8);
        String error = (String) results.get("FAILURE");
        int marks = (int) (results.get("SUCCESS") != null ? results.get("SUCCESS") : 0);

        logger.info("Error for 8: " + error);
        logger.info("Success for 8: " + marks);

        Map<String, Object> results2 = computeWithoutEitherUsingMapJava(86);
        String error2 = (String) results2.get("FAILURE");
        int marks2 = (int) results2.get("SUCCESS");

        logger.info("Error for 86: " + error2);
        logger.info("Success for 86: " + marks2);
    }

    /**
     * VAVR
     */
    private static Either<String, Integer> computeWithEither(int marks) {
        if (marks < 85) {
            return Either.left("Marks not acceptable");
        } else {
            return Either.right(marks);
        }
    }

    private static void vavrConsumer() {
        Either<String, Integer> result = computeWithEither(8);
        String error = result.getLeft();
        int marks = result.right().getOrElse(0);

        logger.info("Error for 8: " + error);
        logger.info("Success for 8: " + marks);

        Either<String, Integer> result2 = computeWithEither(86);
        String error2 = result2.left().getOrNull();
        int marks2 = result2.right().getOrElse(0);

        logger.info("Error for 86: " + error2);
        logger.info("Success for 86: " + marks2);
    }

    public static void main(String[] args) {
        System.out.println("Plain java:");
        plainJavaConsumer();
        System.out.println("\nVavr:");
        vavrConsumer();
    }
}
