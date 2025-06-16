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
        return productsMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getCost)
                .sum();
    }

    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        productsMap.values().stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        long specialCount = getSpecialCount();
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    private long getSpecialCount() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }

    public void clear() {
        productsMap.clear();
    }
}



