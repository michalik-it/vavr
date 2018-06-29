package it.michalik.controller;

import io.vavr.Tuple2;
import io.vavr.control.Option;
import it.michalik.model.User;
import it.michalik.service.UserService;
import org.apache.log4j.Logger;

import java.util.Optional;

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
     */
    public void fetchUserNameAndHeightByIdAndLogItVavr(int id) {
        Tuple2<String, Integer> userNameAndHeight = userService.getUserNameAndHeightById(id);
        log.info("User name:" + userNameAndHeight._1());
        log.info("User height:" + userNameAndHeight._2());
    }


}
