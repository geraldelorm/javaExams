package com.turntabl;

import java.util.*;

public class ParisTradedProductsImplementation implements ParisTradedProducts{
    private Map<Products, Integer> mapOfTradedProducts = new HashMap<>();
    private List<Products> listOfRegisteredProducts = new ArrayList<>();

    @Override
    public void addNewProduct(Products product) throws ProductAlreadyRegisteredException {
        Optional<Products> optionalProduct = this.listOfRegisteredProducts.stream()
                .filter(p -> p.getProductId().equals(product.getProductId()))
                .findFirst();
        if (optionalProduct.isEmpty()) { // is an empty list is returned we know the product doesn't exist
            this.listOfRegisteredProducts.add(product);
        } else {
            throw new ProductAlreadyRegisteredException("The product you are trying to add, already exists");
        }
    }

    @Override
    public void trade(Products product, int quantity) {
        // Exit if the product is not already added to the List

        if (listOfRegisteredProducts.contains(product)) {
            //if Product is already added we increase the quantity, but we need the existing quantity first
            if (mapOfTradedProducts.containsKey(product)) {
                int existingQty = mapOfTradedProducts.get(product);
                int newQty = existingQty + quantity;
                mapOfTradedProducts.put(product, newQty);
            } else {
                mapOfTradedProducts.put(product, quantity);
            }
        }

    }

    @Override
    public int totalTradeQuantityForDay() {
        return this.mapOfTradedProducts.values().stream()
                .reduce(0, Integer::sum);
    }

    @Override
    public double totalValueOfDaysTradedProducts() {
        return this.mapOfTradedProducts.keySet().stream()
                .mapToDouble(product -> product.getCurrentPrice() * mapOfTradedProducts.get(product))
                .sum();
    }
}
