package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Arrays;

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

    public void printBasket() {
        boolean empty = true;
        int specialCount = 0;

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

    public boolean containsProduct(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }


    public void clear() {
        Arrays.fill(products, null);
        count = 0;
    }


}


