package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int BASKET_CAPACITY = 5;
    private final Product[] products;
    private int count;

    public ProductBasket() {
        this.products = new Product[BASKET_CAPACITY];
        this.count = 0;
    }


    public void addProduct(Product product) {
        if (count < BASKET_CAPACITY) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getCost();
        }
        return total;
    }

    public void printContents() {
        if (count == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.printf("%s: %d%n", products[i].getName(), products[i].getCost());
        }
        System.out.printf("Итого: %d%n", getTotalCost());
    }

    public boolean containsProduct(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < BASKET_CAPACITY; i++) {
            products[i] = null;
        }
        count = 0;
    }
}


