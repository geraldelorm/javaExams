package com.turntabl.Products;

import com.turntabl.ServicesAndLogic.ProductPricingService;

//this ia a kind of product
public class Option extends Products {
    private String ticker;
    private int validForDays;

    private final ProductPricingService productPricingService;

    public Option(String productId, String exchange, String ticker, int validForDays, ProductPricingService productPricingService) {
        super(productId, exchange);
        this.ticker = ticker;
        this.validForDays = validForDays;
        this.productPricingService = productPricingService;
    }

    @Override
    public double getCurrentPrice() {
        return this.productPricingService.price(this.getExchange(), this.ticker, this.validForDays);
    }

    //Getter and setters can be implemented latter, they are not needed now

}
