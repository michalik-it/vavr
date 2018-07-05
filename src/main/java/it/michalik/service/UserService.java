package it.michalik.service;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import io.vavr.control.Try;
import it.michalik.UserNotFoundException;
import it.michalik.model.User;
import it.michalik.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Vavr vs Java implementation examples
 */
public class UserService {
    private UserRepository userRepository = new UserRepository();

    /* Option */
    public User findByIdJava(int id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByIdJava8(int id) {
        return Optional.ofNullable(userRepository.findById(id));
    }
    public Option<User> findByIdVavr(int id) {
        return Option.of(userRepository.findById(id));
    }

    /* Tuples */
    public List<Object> getUserNameAndHeightByIdJava(int id) {
        User user = userRepository.findById(id);
        return Arrays.asList(user.getName(), user.getHeight());
    }
    public Tuple2<String, Integer> getUserNameAndHeightByIdVavr(int id) {
        User user = userRepository.findById(id);
        return Tuple.of(user.getName(), user.getHeight());
    }

    /* Try */
    public User searchByIdJava(int id) throws UserNotFoundException {
        return userRepository.searchById(id);
    }

    public Try<User> searchByIdVavr(int id) {
        return Try.of(() -> userRepository.searchById(id));
    }
}
