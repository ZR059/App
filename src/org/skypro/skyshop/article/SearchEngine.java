package org.skypro.skyshop.article;

import org.skypro.skyshop.exception.BestResultsNotFound;
import org.skypro.skyshop.product.Product;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Product> products = new HashSet<>();

    public TreeSet<Product> search(String query) {
        String[] queryWords = query.toLowerCase().split("\\s+");

        return products.stream()
                .filter(product -> Arrays.stream(queryWords)
                        .allMatch(word -> product.getName().toLowerCase().contains(word)))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Product::getName))));
    }

    public void add(Product product) {
        products.add(product);
    }

    public Searchable findBestMatch(Searchable[] items, String search) throws BestResultsNotFound {
        if (items == null || items.length == 0) {
            throw new BestResultsNotFound(search);
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable item : items) {
            String term = item.getSearchTerm();
            int count = countSubstringOccurances(term, search);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (maxCount == 0) {
            throw new BestResultsNotFound(search);
        }
        return bestMatch;
    }

    private int countSubstringOccurances(String str, String substring) {
        int count = 0;
        int index = 0;
        int substringLength = substring.length();

        if (substringLength == 0) return 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substringLength;
        }
        return count;
    }
}
