package com.turntabl;

//This is an abstract class so that we can have "kinds of" products
public abstract class Products {
    private String productId, exchange;

    //constructor
    public Products(String productId, String exchange) {
        this.productId = productId;
        this.exchange = exchange;
    }

    //getter for productID
    public String getProductId() {
        return productId;
    }

    //getter for exchange
    public String getExchange() {
        return exchange;
    }

    //setter for exchange
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    //setter for productID
    public void setProductId(String productId) {
        this.productId = productId;
    }

    //this is abstract because it is impossible to calculate without knowing what kind of product.
    public abstract double getCurrentPrice();
}
