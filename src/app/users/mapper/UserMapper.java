package app.users.mapper;

import app.users.dto.UserCreateResponse;
import app.users.dto.UserDto;
import app.users.dto.UserLoginResponse;
import app.users.model.User;

public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUsername(),
                user.getRole(),
                user.getExtraPermissions(),
                user.getAllPermissions()
        );
    }

    public UserCreateResponse toCreateResponse(User user) {
        return new UserCreateResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUsername(),
                user.getRole(),
                "User created successfully"
        );
    }

    public UserLoginResponse toLoginResponse(User user) {
        return new UserLoginResponse(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getRole(),
                "Login successful"
        );
    }
}
