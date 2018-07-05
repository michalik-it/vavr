package it.michalik.examples;

import org.apache.log4j.Logger;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static io.vavr.Predicates.isNull;

/**
 * Pattern Matching
 *
 */
public class PatternMatchingExample {

    private static Logger logger = Logger.getLogger(PatternMatchingExample.class);

    public static void main(String[] args) {
        logger.info(dummyMethodJava(2));
//        logger.info(dummyMethodVavr(5));
//        dummyMethodWithSideEffectsVavr(20);
    }

    private static String dummyMethodJava(int input) {
        String output;
        if (input == 0) {
            output = "zero";
        }
        else if (input == 1) {
            output = "one";
        }
        else if (input == 2) {
            output = "two";
        }
        else if (input == 3) {
            output = "three";
        }
        else {
            output = "other";
        }

//        switch (input) {
//            case 0:
//                output = "zero";
//                break;
//            case 1:
//                output = "one";
//                break;
//            case 2:
//                output = "two";
//                break;
//            case 3:
//                output = "three";
//                break;
//            default:
//                output = "unknown";
//                break;
//        }
//
        return output;
    }

    private static String dummyMethodVavr(int input) {
        return Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(isIn(4, 5, 6)), "four or five or six"),
                Case($(isNull()), "is null"),
                Case($(n -> n == 7), "seven"), //custom predicate
                Case($(), "other"));
    }

    private static void dummyMethodWithSideEffectsVavr(int input) {
        Match(input).of(
                Case($(isIn(2, 4, 6, 8)), o -> run(() -> logger.info(o))),
                Case($(isIn(1, 3, 5, 7, 9)), o -> run(() -> logger.info(o))),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(String.valueOf(input));
                })));
    }

}
