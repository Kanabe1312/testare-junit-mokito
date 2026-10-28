package app.users.repository;

import app.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserById(int id);

    User saveUser(User user);

    User updateUser(User user);

    List<User> listUsers();


}
