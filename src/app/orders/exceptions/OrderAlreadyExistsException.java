package app.orders.exceptions;

import app.system.utils.Constants;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException() {
        super(Constants.ORDER_ALREADY_EXISTS_EXCEPTION);
    }
}
