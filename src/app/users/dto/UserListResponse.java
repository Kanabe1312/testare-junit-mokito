package app.users.dto;

import java.util.List;

public class UserListResponse {
    private final List<UserDto> users;

    public UserListResponse(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}
