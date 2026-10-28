package app.users.service;

import app.users.dto.UserDto;
import app.users.dto.UserLoginRequest;
import app.users.dto.UserLoginResponse;
import app.users.dto.UserListResponse;
import app.users.exceptions.UserNotFoundException;
import app.users.mapper.UserMapper;
import app.users.model.Customer;
import app.users.model.User;
import app.users.repository.UserRepository;
import app.users.repository.UserRepositorySingleton;

import java.util.List;
import java.util.Optional;

public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserQueryServiceImpl() {
        this.userRepository = UserRepositorySingleton.getINSTANCE();
        this.userMapper = new UserMapper();
    }

    @Override
    public void showCustomers() {

        userRepository.listUsers().stream()
                .filter(user -> user instanceof Customer)
                .forEach(System.out::println);

    }

    @Override
    public void showUsers() {

        userRepository.listUsers()
                .forEach(System.out::println);

    }

    @Override
    public UserListResponse findAllUsers() {
        return new UserListResponse(findAllUserDtos());
    }

    @Override
    public List<UserDto> findAllUserDtos() {
        return userRepository.listUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserListResponse findCustomers() {
        return new UserListResponse(findCustomerDtos());
    }

    @Override
    public List<UserDto> findCustomerDtos() {
        return userRepository.listUsers().stream()
                .filter(user -> user instanceof Customer)
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) throws UserNotFoundException {
        return userMapper.toLoginResponse(loginUser(request.getUsername(), request.getPassword()));
    }

    @Override
    public UserDto loginUserDto(String username, String password) throws UserNotFoundException {
        return userMapper.toDto(loginUser(username, password));
    }

    public User loginUser(String username, String password) throws UserNotFoundException {

        Optional<User> searchedUser = userRepository.findUserByUsernameAndPassword(username,password);

        if(searchedUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        return searchedUser.get();
        }


    }









