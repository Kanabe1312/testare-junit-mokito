package app.users.service;

import app.users.dto.UserPermissionGrantResponse;
import app.users.dto.UserCreateRequest;
import app.users.dto.UserCreateResponse;
import app.users.exceptions.UserAlreadyExistException;
import app.users.exceptions.UserNotFoundException;
import app.users.factory.UserFactory;
import app.users.factory.UserFactorySingleton;
import app.users.mapper.UserMapper;
import app.users.model.Permission;
import app.users.model.User;
import app.users.repository.UserRepository;
import app.users.repository.UserRepositorySingleton;

import java.util.Optional;

public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final UserMapper userMapper;

    public UserCommandServiceImpl() {
        userRepository = UserRepositorySingleton.getINSTANCE();
        userFactory = UserFactorySingleton.getINSTANCE();
        userMapper = new UserMapper();
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        Optional<User> searchedUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (searchedUser.isPresent()) {
            throw new UserAlreadyExistException();
        }

        return this.userRepository.saveUser(user);
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest request) throws UserAlreadyExistException {
        User user = userFactory.createFromRequest(request);
        User savedUser = registerUser(user);
        return userMapper.toCreateResponse(savedUser);
    }

    @Override
    public UserPermissionGrantResponse grantExtraPermission(int userId, Permission permission) throws UserNotFoundException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (user.getRole().hasPermission(permission)) {
            return new UserPermissionGrantResponse(
                    user.getId(),
                    user.getUsername(),
                    permission,
                    "Permisiunea exista deja prin rolul utilizatorului"
            );
        }

        if (!user.addExtraPermission(permission)) {
            return new UserPermissionGrantResponse(
                    user.getId(),
                    user.getUsername(),
                    permission,
                    "Permisiunea extra exista deja pentru utilizator"
            );
        }

        userRepository.updateUser(user);
        return new UserPermissionGrantResponse(
                user.getId(),
                user.getUsername(),
                permission,
                "Permisiunea extra a fost adaugata"
        );
    }
}
