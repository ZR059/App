package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        SimpleProduct apple = new SimpleProduct("Яблоко", 50);
        DiscountedProduct banana = new DiscountedProduct("Банан", 70, 10);
        FixPriceProduct orange = new FixPriceProduct("Апельсин");
        SimpleProduct milk = new SimpleProduct("Молоко", 80);
        SimpleProduct bread = new SimpleProduct("Хлеб", 40);
        SimpleProduct cheese = new SimpleProduct("Сыр", 120);

        //Добавляем корзину
        ProductBasket basket = new ProductBasket();

        // 1. Добавление продукта в корзину
        basket.addProduct(apple);
        basket.addProduct(banana);
        basket.addProduct(orange);
        basket.addProduct(milk);
        basket.addProduct(bread);

        // 2. Добавление продукта в заполненную корзину
        basket.addProduct(cheese); // Должно вывести "Невозможно добавить продукт"

        // 3. Печать содержимого корзины с несколькими товарами
        System.out.println("Содержимое корзины:");
        basket.printBasket();

        // 4. Получение стоимости корзины с несколькими товарами
        System.out.println("Общая стоимость: " + basket.getTotalCost());

        // 5. Поиск товара, который есть в корзине
        System.out.println("Есть ли в корзине Банан? " + basket.containsProduct("Банан"));

        // 6. Поиск товара, которого нет в корзине
        System.out.println("Есть ли в корзине Сыр? " + basket.containsProduct("Сыр"));

        // 7. Очистка корзины
        basket.clear();

        // 8. Печать содержимого пустой корзины
        System.out.println("Содержимое корзины после очистки:");
        basket.printBasket();

        // 9. Получение стоимости пустой корзины
        System.out.println("Общая стоимость пустой корзины: " + basket.getTotalCost());

        // 10. Поиск товара по имени в пустой корзине
        System.out.println("Есть ли в пустой корзине Яблоко? " + basket.containsProduct("Яблоко"));
    }
}