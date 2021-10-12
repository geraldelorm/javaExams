package com.turntabl;

public class Futures extends Products {
    private String contractCode;
    private int month, year;

    private final ProductPricingService productPricingService;

    public Futures(String productId, String exchange, String contractCode, int month, int year, ProductPricingService productPricingService) {
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
