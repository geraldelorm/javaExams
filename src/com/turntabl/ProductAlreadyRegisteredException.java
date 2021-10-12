package com.turntabl;

public class ProductAlreadyRegisteredException extends Exception{
    // To be able to pass in our own messages we need the constructor and getMessage Method
    public ProductAlreadyRegisteredException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
