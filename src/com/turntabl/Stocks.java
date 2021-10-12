package com.turntabl;

//this is a "kind of" product
public class Stocks extends Products {
    private String ticker;

    private final ProductPricingService productPricingService;

    public Stocks(String productId, String exchange, String ticker, ProductPricingService productPricingService) {
        super(productId, exchange);
        this.ticker = ticker;
        this.productPricingService = productPricingService;
    }

    @Override
    public double getCurrentPrice() {
        return this.productPricingService.price(this.getExchange(), this.ticker);
    }

    //Getter and setters can be implemented latter, they are not needed now

}
