package com.turntabl.Tests;

import com.turntabl.Exceptions.ProductAlreadyRegisteredException;
import com.turntabl.Products.Future;
import com.turntabl.Products.Option;
import com.turntabl.Products.Products;
import com.turntabl.Products.Stock;
import com.turntabl.ServicesAndLogic.ProductPricingService;
import com.turntabl.ServicesAndLogic.ParisTradedProductsActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//All the tests bellow where written first before implementation
class ProductsTest {
    Products product;
    ProductPricingService productPricingService;
    ParisTradedProductsActions parisTradeAction;

    //This is run before each Test Case
    @BeforeEach
    void setup() {
        parisTradeAction = new ParisTradedProductsActions();
        product = mock(Products.class);
        when(product.getProductId()).thenReturn("1");

        productPricingService = mock(ProductPricingService.class);
        // Option pricingService mock return
        when(productPricingService.price(anyString(), anyString(), anyInt())).thenReturn(2.0);
        //stock pricingService mock return
        when(productPricingService.price(anyString(), anyString())).thenReturn(5.0);
        // future pricingService mock return
        when(productPricingService.price(anyString(), anyString(), anyInt(), anyInt())).thenReturn(10.0);
    }

    @Test
    public void productCanBeAddedToListAndDuplicateProductsThrowsException() throws ProductAlreadyRegisteredException {
        //add product
        parisTradeAction.addNewProduct(product);
        //exception due to adding again
        assertThrows(ProductAlreadyRegisteredException.class, () -> parisTradeAction.addNewProduct(product));
    }

    @Test
    public void productCanBeTradedAfterBeenAddedToListOfProducts() throws ProductAlreadyRegisteredException {
        //add product to list
        parisTradeAction.addNewProduct(product);
        //trade the product ie add to map
        parisTradeAction.trade(product, 15);
    }


    @Test
    public void checkThatProductTradedQuantityIsValid() throws ProductAlreadyRegisteredException {
        //add and trade product
        parisTradeAction.addNewProduct(product);
        parisTradeAction.trade(product, 10);
        //create add and trade another product
        Products newProduct = mock(Products.class);
        parisTradeAction.addNewProduct(newProduct);
        parisTradeAction.trade(newProduct, 30);

        assertEquals(40, parisTradeAction.totalTradeQuantityForDay());
    }

    @Test
    public void totalValueOfDaysTradedProductsIsValid() throws ProductAlreadyRegisteredException {
        Products aStock = new Stock("1", "GGP", "EXE", productPricingService);
        parisTradeAction.addNewProduct(aStock);
        parisTradeAction.trade(aStock, 4);

        Products aFuture = new Future("2", "GHA", "codeRed", 9, 16, productPricingService);
        parisTradeAction.addNewProduct(aFuture);
        parisTradeAction.trade(aFuture, 5);

        Products anOption = new Option("4", "EXE", "APB", 9, productPricingService);
        parisTradeAction.addNewProduct(anOption);
        parisTradeAction.trade(anOption, 30);

        assertEquals(130.0, parisTradeAction.totalValueOfDaysTradedProducts());
    }

}