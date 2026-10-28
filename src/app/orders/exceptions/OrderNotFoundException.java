package app.orders.exceptions;

import app.system.utils.Constants;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super(Constants.ORDER_NOT_FOUND_EXCEPTION);
    }
}
