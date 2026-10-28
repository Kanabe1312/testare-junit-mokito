package app.products.exceptions;

import app.system.utils.Constants;

public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException() {
        super(Constants.PRODUCT_ALREADY_EXISTS_EXCEPTION);
    }
}
