package app.users.exceptions;

import app.system.utils.Constants;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND_EXCEPTION);
    }
}
