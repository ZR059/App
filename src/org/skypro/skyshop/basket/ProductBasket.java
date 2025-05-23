package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> productsMap = new HashMap<>();

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = productsMap.remove(name);
        return removed != null ? removed : Collections.emptyList();
    }

    public void addProduct(Product product) {
        String name = product.getName();
        if (!productsMap.containsKey(name)) {
            productsMap.put(name, new ArrayList<>());
        }
        productsMap.get(name).add(product);
    }

    public int getTotalCost() {
        int total = 0;
        for (List<Product> products : productsMap.values()) {
            for (Product product : products) {
                total += product.getCost();
            }
        }
        return total;
    }

    public void printBasket() {
        boolean empty = true;
        int specialCount = 0;

        for (List<Product> products : productsMap.values()) {
            for (Product product : products) {
                if (product != null) {
                    System.out.println(product);
                    if (product.isSpecial()) {
                        specialCount++;
                    }
                    empty = false;
                }
            }
            if (empty) {
                System.out.println("в корзине пусто");
            } else {
                System.out.println("Итого: " + getTotalCost());
                System.out.println("Специальных товаров: " + specialCount);
            }
        }
    }

    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }

    public void clear() {
        productsMap.clear();
    }
}



