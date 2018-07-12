package it.michalik.examples;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.apache.log4j.Logger;

/**
 * Collections (http://www.baeldung.com/vavr-collections)
 * Immutable
 * A persistent collection when modified produces a new version of the collection while preserving the current version.
 * Maintaining multiple versions of the same collection might lead to inefficient CPU and memory usage. However, the Vavr collection library overcomes this by sharing data structure across different versions of a collection.
 *
 *
 * 1. Traversable
 *
 * Traversable is the base type of all Vavr collections – this interface defines methods that are shared among all data structures.
 *
 * It provides some useful default methods such as size(), get(), filter(), isEmpty() and others which are inherited by sub-interfaces.
 *
 * Let’s explore the collections library further.
 *
 * 2. Seq
 *
 * We’ll start with sequences.
 *
 * The Seq interface represents sequential data structures. It is the parent interface for List, Stream, Queue, Array, Vector, and CharSeq.
 *
 * 3. List
 *
 * A List is an eagerly-evaluated sequence of elements extending the LinearSeq interface.
 *
 *
 * 4. Stream
 *
 * A Stream is an implementation of a lazy linked list and is quite different from java.util.stream. Unlike java.util.stream,
 * the Vavr Stream stores data and is lazily evaluating next elements.
 * This behaviour can improve performance and makes it possible to use Stream to represent sequences that are (theoretically) infinitely long.
 */
public class CollectionsExample {
    private static Logger logger = Logger.getLogger(CollectionsExample.class);

    public static void main(String[] args) {
        //listExample();

        //STREAM
        Stream<Integer> s = Stream.of(2, 1, 3, 4);
        logger.info("Stream: " + s);
        s.get(2);
        logger.info(s);
    }

    private static void listExample() {
        List<String> listOrg = List.of("Java", "PHP", "Jquery", "JavaScript", "JShell", "JAVA", "JAVA");
        List list1 = listOrg.drop(2);
        logger.info("listOrg: " + listOrg);
        logger.info("list1: " + list1);
        List list2 = listOrg.dropRight(2);
        List list3 = listOrg.dropUntil(s -> s.contains("Shell"));
        logger.info("list2: " + list2);
        logger.info("list3: " + list3);
        List list4 = listOrg.distinct();
        logger.info("list4: " + list4);
        List list5 = listOrg.distinctBy((s1, s2) -> s1.equalsIgnoreCase(s2) ? 0 : 1);
        logger.info("list5 " + list5);
    }
}
