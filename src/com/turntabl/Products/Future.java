package com.turntabl.Products;

import com.turntabl.ServicesAndLogic.ProductPricingService;

//this ia a kind of product
public class Future extends Products {
    private String contractCode;
    private int month, year;

    private final ProductPricingService productPricingService;

    public Future(String productId, String exchange, String contractCode, int month, int year, ProductPricingService productPricingService) {
        super(productId, exchange);
        this.contractCode = contractCode;
        this.month = month;
        this.year = year;
        this.productPricingService = productPricingService;
    }

    @Override
    public double getCurrentPrice() {
        return this.productPricingService.price(this.getExchange(), this.contractCode, this.month, this.year);
    }

    //Getter and setters can be implemented latter, they are not needed now
}
