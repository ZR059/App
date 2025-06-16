package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.article.SearchEngine;
import org.skypro.skyshop.article.Searchable;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.exception.BestResultsNotFound;


import java.util.List;
import java.util.Set;


public class App {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine();
        try {
            Product invalidProduct = new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось создать продукт: " + e.getMessage());
        }

        try {
            SimpleProduct invalidPriceProduct = new SimpleProduct("Книга", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось создать продукт: " + e.getMessage());
        }

        try {
            DiscountedProduct invalidDiscountProduct = new DiscountedProduct("Стул", 1500, 101);
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось создать продукт: " + e.getMessage());
        }

        //Поиск
        Searchable[] products = new Searchable[]{
                new SimpleProduct("hello world hello", 100),
                new SimpleProduct("hello", 200),
                new SimpleProduct("world", 300),
                new DiscountedProduct("hello hello hello", 400, 10)
        };

        try {
            Searchable bestMatch = searchEngine.findBestMatch(products, "hello");
            System.out.println("Лучший результат: " + bestMatch.getSearchTerm());
        } catch (BestResultsNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable noMatch = searchEngine.findBestMatch(products, "abc");
        } catch (BestResultsNotFound e) {
            System.out.println(e.getMessage());
        }

        SimpleProduct apple = new SimpleProduct("Яблоко", 50);
        DiscountedProduct banana = new DiscountedProduct("Банан", 70, 10);
        FixPriceProduct orange = new FixPriceProduct("Апельсин");
        SimpleProduct milk = new SimpleProduct("Молоко", 80);
        SimpleProduct bread = new SimpleProduct("Хлеб", 40);
        SimpleProduct cheese = new SimpleProduct("Сыр", 120);

        searchEngine.add(new SimpleProduct("Ноутбук", 30000));
        searchEngine.add(new SimpleProduct("Телевизор", 40000));
        searchEngine.add(new SimpleProduct("Смартфон", 50000));

        searchEngine.add(new Article("Выбор ноутбука", "На что стоит обращать внимание при выборе ноутбука?"));
        searchEngine.add(new Article("Какой телевизор стоит приобрести?", "Какие параметры влияют на выбор телевизора?"));
        searchEngine.add(new Article("Самый популярный смартфон", "В данной статье модели самых популярных смартфонов"));


        System.out.println("Результаты поиска для 'ноут': ");
        printResults(searchEngine.search("ноут"));

        System.out.println("\nРезультаты поиска для 'телевизор': ");
        printResults(searchEngine.search("телевизор"));

        System.out.println("\nРезультаты поиска для 'смартфон: ");
        printResults(searchEngine.search("смартфон"));

        System.out.println("\nРезультаты поиска для 'самый популярный': ");
        printResults(searchEngine.search("самый популярный"));

        // Создаём корзину
        ProductBasket basket = new ProductBasket();

        // Добавление продукта в корзину
        basket.addProduct(apple);
        basket.addProduct(banana);
        basket.addProduct(orange);
        basket.addProduct(milk);
        basket.addProduct(bread);

        // Печать содержимого корзины с несколькими товарами
        System.out.println("\nСодержимое корзины:");
        basket.printBasket();

        //Удаляем существующий продукт
        System.out.println("\n===Удаляем яблоко===");
        List<Product> removed = basket.removeProductsByName("Яблоко");
        if (!removed.isEmpty()) {
            System.out.println("Удаленные продукты: ");
            for (Product product : removed) {
                System.out.println(product);
            }
        } else {
            System.out.println("Список удаленных продуктов пуст");
        }
        System.out.println("\nСодержимое корзины после удаления");
        basket.printBasket();


        // Удаляем не существующий продукт
        System.out.println("\n===Удаляем сыр===");
        List<Product> notFound = basket.removeProductsByName("Сыр");
        if (notFound.isEmpty()) {
            System.out.println("Продукта нет в корзине");
        }
        System.out.println("\nСодержимое корзины после удаления: ");
        basket.printBasket();

        // Получение стоимости корзины с несколькими товарами
        System.out.println("\nОбщая стоимость: " + basket.getTotalCost());

        // Поиск товара, который есть в корзине
        System.out.println("\nЕсть ли в корзине Банан? " + basket.containsProduct("Банан"));

        // Поиск товара, которого нет в корзине
        System.out.println("\nЕсть ли в корзине Сыр? " + basket.containsProduct("Сыр"));

        // Очистка корзины
        basket.clear();

        // Печать содержимого пустой корзины
        System.out.println("\nСодержимое корзины после очистки:");
        basket.printBasket();

        // Получение стоимости пустой корзины
        System.out.println("\nОбщая стоимость пустой корзины: " + basket.getTotalCost());

        // Поиск товара по имени в пустой корзине
        System.out.println("\nЕсть ли в пустой корзине Яблоко? " + basket.containsProduct("Яблоко"));
    }

    private static void printResults(Set<? extends Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
            return;
        }

        System.out.println("Найдено результатов: " + results.size());

        for (Searchable item : results) {
            System.out.println("- " + item.getName());
        }
    }
}