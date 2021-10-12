package com.turntabl.ServicesAndLogic;

public interface ProductPricingService {
    double price(String exchange, String ticker);
    double price(String exchange, String contractCode, int month, int year);
    double price(String exchange, String ticker, int validForDays);
}
