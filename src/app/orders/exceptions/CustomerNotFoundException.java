package app.orders.exceptions;

import app.system.utils.Constants;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException() {
    super(Constants.CUSTOMER_NOT_FOUND_EXCEPTION);
  }
}
