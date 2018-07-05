package it.michalik.repository;

import io.vavr.collection.List;
import it.michalik.UserNotFoundException;
import it.michalik.model.User;

/**
 * Maybe some external library like Spring JPA Data
 */
public class UserRepository {

    List<User> users = List.of(
            User.builder().id(1).name("Kamil").height(183).cv("1GB CV OMG!").build(),
            User.builder().id(1).name("Marek").height(183).cv("1GB CV OMG!").build(),
            User.builder().id(1).name("Konrad").height(183).cv("1GB CV OMG!").build(),
            User.builder().id(1).name("Kamil").height(183).cv("1GB CV OMG!").build(),
            User.builder().id(1).name("Marcelina").height(183).cv("1GB CV OMG!").build(),
            User.builder().id(1).name("Angela").height(183).cv("1GB CV OMG!").build()
    );

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
       return null;
    }

    public User searchById(int id) throws UserNotFoundException {
        if (id < 0) {
            throw new IllegalArgumentException("User id cannot be lower then 0");
        }
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }
}
