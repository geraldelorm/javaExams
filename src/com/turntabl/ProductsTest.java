package com.turntabl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsTest {

    ParisTradedProductsImplementation parisTradedProductsImplementation;
    Products product;
    ProductPricingService productPricingService;

    //This is run before each Test Case
    @BeforeEach
    void setup() {
        parisTradedProductsImplementation = new ParisTradedProductsImplementation();
        product = mock(Products.class);
        when(product.getProductId()).thenReturn("1");

        productPricingService = mock(ProductPricingService.class);
        //stock
        when(productPricingService.price(anyString(), anyString())).thenReturn(5.0);
        // future
        when(productPricingService.price(anyString(), anyString(), anyInt(), anyInt())).thenReturn(10.0);
        // Option
        when(productPricingService.price(anyString(), anyString(), anyInt())).thenReturn(2.0);
    }

    @Test
    public void productCanBeTraded() throws ProductAlreadyRegisteredException {
        //add product to list
        parisTradedProductsImplementation.addNewProduct(product);
        //trade the product ie add to map
        parisTradedProductsImplementation.trade(product, 15);
    }

    @Test
    public void productCanBeAddedAndDuplicateProductThrowsException() throws ProductAlreadyRegisteredException {
        //add product
        parisTradedProductsImplementation.addNewProduct(product);
        //exception due to adding again
        assertThrows(ProductAlreadyRegisteredException.class, () -> parisTradedProductsImplementation.addNewProduct(product));
    }

    @Test
    public void productTradedQuantityIsValid() throws ProductAlreadyRegisteredException {
        //add and trade product
        parisTradedProductsImplementation.addNewProduct(product);
        parisTradedProductsImplementation.trade(product, 10);

        //create add and trade another product
        Products newProduct = mock(Products.class);
        parisTradedProductsImplementation.addNewProduct(newProduct);
        parisTradedProductsImplementation.trade(newProduct, 30);

        assertEquals(40, parisTradedProductsImplementation.totalTradeQuantityForDay());
    }

    @Test
    public void totalValueOfDaysTradedProductsIsValid() throws ProductAlreadyRegisteredException {
        Products aStock = new Stocks("1", "GGP", "EXE", productPricingService);
        parisTradedProductsImplementation.addNewProduct(aStock);
        parisTradedProductsImplementation.trade(aStock, 4);


        Products aFuture = new Futures("2", "GHA", "codeRed", 9, 16, productPricingService);
        parisTradedProductsImplementation.addNewProduct(aFuture);
        parisTradedProductsImplementation.trade(aFuture, 5);

        Products anOption = new Option("4", "EXE", "APB", 9, productPricingService);
        parisTradedProductsImplementation.addNewProduct(anOption);
        parisTradedProductsImplementation.trade(anOption, 30);

        assertEquals(130.0, parisTradedProductsImplementation.totalValueOfDaysTradedProducts());
    }

}