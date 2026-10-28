package app.users.service;

import app.system.utils.Data;
import app.users.dto.UserDto;
import app.users.dto.UserLoginRequest;
import app.users.dto.UserLoginResponse;
import app.users.dto.UserListResponse;
import app.users.exceptions.UserNotFoundException;
import app.users.model.User;

import java.util.List;

public interface UserQueryService extends Data {

    void showCustomers();

    void showUsers();

    UserListResponse findAllUsers();

    List<UserDto> findAllUserDtos();

    UserListResponse findCustomers();

    List<UserDto> findCustomerDtos();

    UserLoginResponse login(UserLoginRequest request) throws UserNotFoundException;

    UserDto loginUserDto(String username, String password) throws UserNotFoundException;

    User loginUser(String username, String password) throws UserNotFoundException;
}
