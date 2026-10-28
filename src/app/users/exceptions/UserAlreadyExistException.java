package app.users.exceptions;

import app.system.utils.Constants;

;

public class UserAlreadyExistException extends Exception {

    public  UserAlreadyExistException(){
        super(Constants.USER_ALREADY_EXIST_EXCEPTION);
    }
}
