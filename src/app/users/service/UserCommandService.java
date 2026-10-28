package app.users.service;

import app.users.dto.UserPermissionGrantResponse;
import app.system.utils.Data;
import app.users.dto.UserCreateRequest;
import app.users.dto.UserCreateResponse;
import app.users.exceptions.UserAlreadyExistException;
import app.users.exceptions.UserNotFoundException;
import app.users.model.Permission;
import app.users.model.User;

public interface UserCommandService extends Data {

    User registerUser(User user) throws UserAlreadyExistException;

    UserCreateResponse createUser(UserCreateRequest request) throws UserAlreadyExistException;

    UserPermissionGrantResponse grantExtraPermission(int userId, Permission permission) throws UserNotFoundException;

}
