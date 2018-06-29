package it.michalik.repository;

import io.vavr.collection.List;
import it.michalik.model.User;

public class UserRepository {

    List<User> users = List.of(
            User.builder().id(1).name("Kamil").height(183).build(),
            User.builder().id(1).name("Marek").height(183).build(),
            User.builder().id(1).name("Konrad").height(183).build(),
            User.builder().id(1).name("Kamil").height(183).build(),
            User.builder().id(1).name("Marcelina").height(183).build(),
            User.builder().id(1).name("Angela").height(183).build()
    );

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
