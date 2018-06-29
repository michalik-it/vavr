package it.michalik.service;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import it.michalik.model.User;
import it.michalik.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public User findByIdJava(int id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByIdJava8(int id) {
        return Optional.ofNullable(userRepository.findById(id));
    }
    public Option<User> findByIdVavr(int id) {
        return Option.of(userRepository.findById(id));
    }
    public Tuple2<String, Integer> getUserNameAndHeightById(int id) {
        User user = userRepository.findById(id);
        return Tuple.of(user.getName(), user.getHeight());
    }
}
