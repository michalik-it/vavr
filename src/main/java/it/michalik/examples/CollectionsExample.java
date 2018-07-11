package it.michalik.examples;

import io.vavr.collection.List;
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
 * The Seq interface represents sequential data structures. It is the parent interface for List, Stream, Queue, Array, Vector, and CharSeq. All these data structures have their own unique properties which we’ll be exploring below.
 *
 * 3. List
 *
 * A List is an eagerly-evaluated sequence of elements extending the LinearSeq interface.
 */
public class CollectionsExample {
    private static Logger logger = Logger.getLogger(CollectionsExample.class);

    public static void main(String[] args) {
        //create a list
        List<String> listOrg = List.of("Java", "PHP", "Jquery", "JavaScript", "JShell", "JAVA", "JAVA");
        List list1 = listOrg.drop(2); //immutable
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
