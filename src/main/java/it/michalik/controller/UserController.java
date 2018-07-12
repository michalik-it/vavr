package it.michalik.controller;

import io.vavr.API;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import io.vavr.control.Try;
import it.michalik.UserNotFoundException;
import it.michalik.model.User;
import it.michalik.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

/**
 * Vavr consuming showcase
 */
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    private UserService userService = new UserService();

    /**
     * Option consuming
     * Option is an object container in Vavr with a similar end goal like Optional in Java 8. Vavr’s Option implements Serializable, Iterable, and has a richer API.
     */

    //fetchUserNameById
    public String fetchUserNameByIdJava(int id) {
        User user = userService.findByIdJava(id);
        if (user == null) {
            return "Error: User does not exist";
        }
        return user.getName();
    }

    public String fetchUserNameByIdJava8(int id) {
        Optional<User> user = userService.findByIdJava8(id);
        return user.map(User::getName).orElse("Error: User does not exist");
    }

    public String fetchUserNameByIdVavr(int id) {
        Option<User> user = userService.findByIdVavr(id);
        return user.map(User::getName).getOrElse("Error: User does not exist");
    }

    //fetchUserNameByIdAndLogIt
    public void fetchUserNameByIdAndLogItJava8(int id) {
        Optional<User> user = userService.findByIdJava8(id);
        if (user.isPresent()) {
            log.info("User name:" + user.get().getName());
        } else {
            log.info("Error: User does not exist");
        }
// ALT
//        user.map(u -> {
//            log.info("User name:" + u.getName());
//            return null;
//        }).orElseGet( () -> {
//            log.info("Error: User does not exist");
//            return null;
//        });
    }

    public void fetchUserNameByIdAndLogItVavr(int id) {
        userService.findByIdVavr(id)
                .peek(u -> log.info("User name:" + u.getName()))
                .onEmpty(() -> log.info("Error: User does not exist"));
    }

    /**
     * Tuples consuming
     * Tuples are immutable and can hold multiple objects of different types in a type-safe manner.
     * <p>
     * Blog (http://blog.vavr.io/vavr-one-log-02/): There are only a few cases where Vavr uses tuples as part of the API. Will be removed in future.
     * Use classes ;)
     */
    public void fetchUserNameAndHeightByIdAndLogItJava(int id) {
        List<Object> userNameAndHeight = userService.getUserNameAndHeightByIdJava(id);
        log.info("User name:" + (String)userNameAndHeight.get(0));
        log.info("User height:" + (String)userNameAndHeight.get(1));
    }

    public void fetchUserNameAndHeightByIdAndLogItVavr(int id) {
        Tuple2<String, Integer> userNameAndHeight = userService.getUserNameAndHeightByIdVavr(id);
        log.info("User name:" + userNameAndHeight._1());
        log.info("User height:" + userNameAndHeight._2());
    }

    /**
     * Try consuming
     * Try is a container for a computation which may result in an exception. Try wraps a computation so that we don’t have to explicitly take care of exceptions with try-catch blocks
     */
    public User searchUserByIdJava_1(int id) {
        try {
            return userService.searchByIdJava(id);
        } catch (Exception e) {
            return null;
        }
    }

    public User searchUserByIdVavr_1(int id) {
        return userService.searchByIdVavr(id).getOrElse((User) null);
    }

    public User searchUserByIdJava_2(int id) {
        try {
            return userService.searchByIdJava(id);
        } catch (Exception e) {
            log.warn(e);
            return null;
        }
    }

    public User searchUserByIdVavr_2(int id) {
        return userService.searchByIdVavr(id).recover(Exception.class, e -> {
            log.warn(e);
            return null;
        }).getOrElse((User) null);
    }

    public User searchUserByIdJava_3(int id) {
        try {
            return userService.searchByIdJava(id);
        } catch (UserNotFoundException e) {
            log.warn(e);
            return null;
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public User searchUserByIdVavr_3(int id) {
        return userService.searchByIdVavr(id).recover(ex -> Match(ex).of(
                Case($(instanceOf(UserNotFoundException.class)), e -> {
                    log.warn(e);
                    return null;
                }),
                Case($(instanceOf(Exception.class)), e -> {
                    log.error(e);
                    throw new RuntimeException(e);
                }))).get();
    }

}
