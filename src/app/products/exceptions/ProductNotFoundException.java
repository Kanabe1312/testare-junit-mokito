package app.products.exceptions;

import app.system.utils.Constants;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super(Constants.PRODUCT_NOT_FOUND_EXCEPTION);
    }
}
